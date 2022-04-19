package fabrick.service.impl;

import org.springframework.util.MultiValueMap;
import java.util.Collections;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import java.util.Date;
import java.util.Collection;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ArrayList;
import fabrick.response.API.TrasanctionsResponse;
import fabrick.service.utils.FabrickCostants;
import fabrick.response.API.Transaction;
import java.util.Map;
import java.util.List;
import fabrick.request.TransactionRequest;
import fabrick.request.API.Account;
import fabrick.request.API.CreateMoneyTransfer;
import fabrick.request.API.Creditor;
import fabrick.request.API.LegalPersonBeneficiary;
import fabrick.request.API.NaturalPersonBeneficiary;
import fabrick.request.API.TaxRelief;
import fabrick.response.BankTransferResponse;
import fabrick.request.BankTransferRequest;
import fabrick.request.BaseRequest;

import org.springframework.http.ResponseEntity;
import fabrick.response.API.BalanceResponse;
import fabrick.response.API.PayloadTransactions;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import fabrick.response.AccountBalanceResponse;

import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

public class FabrickServiceBean {
	private RestTemplate restTemplate;

	public AccountBalanceResponse getBalance(final BaseRequest request)
			throws JsonParseException, JsonMappingException, IOException {
		AccountBalanceResponse resp = new AccountBalanceResponse();
		try {
			Map<String, Long> params = new HashMap<String, Long>();
			params.put("accountId", request.getAccountId());
			HttpEntity<String> header = new HttpEntity(setHttpHeaders());
			ResponseEntity<String> entity = (ResponseEntity<String>) this.restTemplate
					.exchange(FabrickCostants.BALANCE_URL, HttpMethod.GET, header, String.class, params);
			String json = (String) entity.getBody();
			ObjectMapper mapper = new ObjectMapper();
			BalanceResponse response = (BalanceResponse) mapper.readValue(json, BalanceResponse.class);
			if (response != null && response.getPayloadBalance() != null) {
				resp.setBalance(response.getPayloadBalance().getAvailableBalance());
			}
		} catch (HttpStatusCodeException e) {
			System.out.println(e.getStackTrace());
		}
		return resp;
	}

	public BankTransferResponse bankTransfer(BankTransferRequest request) {

		BankTransferResponse resp = new BankTransferResponse();
		CreateMoneyTransfer createMoneyTransfer = new CreateMoneyTransfer();
		String entity;
		try {
			Map<String, Long> params = new HashMap<String, Long>();
			params.put("accountId", request.getAccountId());
			createMoneyTransfer = mapRequestAPI(request);
			JSONObject money = new JSONObject(createMoneyTransfer);
			money.getJSONObject("taxRelief").put("isCondoUpgrade", false);
			HttpEntity<Map> requestEntity = new HttpEntity<>(money.toMap(), setHttpHeaders());
			entity = restTemplate.postForObject(FabrickCostants.BANK_TRANSFER, requestEntity, String.class, params);
			JSONObject entityresponse = new JSONObject(entity);
			resp.setCode(entityresponse.getString("status"));
			resp.setDescriptions(FabrickCostants.DESCRIPTION);
		} catch (HttpStatusCodeException e) {
			e.getResponseBodyAsString();
			JSONObject obj = new JSONObject(e.getResponseBodyAsString());
			resp.setDescriptions(
					"Errore tecnico La condizione BP049 non e&#39; prevista per il conto id" + request.getAccountId());
			resp.setCode("API000");
		}
		return resp;
	}

	private CreateMoneyTransfer mapRequestAPI(BankTransferRequest request) {
		CreateMoneyTransfer createMoneyTransfer = new CreateMoneyTransfer();
		Creditor creditor = new Creditor();
		Account account = new Account();

		account.setAccountCode(request.getAccountCode());
		TaxRelief taxRelief = new TaxRelief();
		if (request.getTaxReliefId() != null)
			taxRelief.setTaxReliefId(request.getTaxReliefId());
		else {
			taxRelief.setTaxReliefId("NaN");
		}
		if (request.getCreditorFiscalCode() != null)
			taxRelief.setCreditorFiscalCode(request.getCreditorFiscalCode());
		else {
			taxRelief.setCreditorFiscalCode("NaN");
		}
		if (request.getBeneficiaryType() != null)
			taxRelief.setBeneficiaryType(request.getBeneficiaryType());
		else {
			taxRelief.setBeneficiaryType("NaN");
		}
		NaturalPersonBeneficiary naturalPersonBeneficiary = new NaturalPersonBeneficiary();
		naturalPersonBeneficiary.setFiscalCode1(request.getFiscalCode1());
		LegalPersonBeneficiary legalPersonBeneficiary = new LegalPersonBeneficiary();
		legalPersonBeneficiary.setFiscalCode(request.getFiscalCode());
		taxRelief.setNaturalPersonBeneficiary(naturalPersonBeneficiary);
		taxRelief.setLegalPersonBeneficiary(legalPersonBeneficiary);
		createMoneyTransfer.setTaxRelief(taxRelief);
		creditor.setName(request.getReceiverName());
		creditor.setAccount(account);
		createMoneyTransfer.setCreditor(creditor);
		createMoneyTransfer.setDescription(request.getDescription());
		createMoneyTransfer.setExecutionDate(request.getExecutionDate());
		if (request.getAmount() != null)
			createMoneyTransfer.setAmount(Integer.valueOf(request.getAmount()));
		createMoneyTransfer.setCurrency(request.getCurrency());

		return createMoneyTransfer;

	}

	public List<Transaction> getTransactions(TransactionRequest request)
			throws JsonParseException, JsonMappingException, IOException {
		List<Transaction> resp = new ArrayList<>();
		try {

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("fromAccountingDate", getQueryDate(request.getFromAccountingDate()));
			params.put("toAccountingDate", getQueryDate(request.getToAccountingDate()));
			params.put("accountId", request.getAccountId());
			ObjectMapper mapper = new ObjectMapper();
			HttpEntity<String> header = new HttpEntity(setHttpHeaders());
			ResponseEntity<String> entity = restTemplate.exchange(FabrickCostants.TRANSACTIONS_URL, HttpMethod.GET,
					header, String.class, params);
			String json = (String) entity.getBody();
			JSONObject obj = new JSONObject(json);
			String payload = obj.getJSONObject("payload").toString();
			mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			resp = mapper.readValue(payload,
					mapper.getTypeFactory().constructCollectionType(List.class, PayloadTransactions.class));
		} catch (HttpStatusCodeException e) {
			System.out.println(e.getStackTrace());
		}
		return resp;
	}

	private LocalDate getQueryDate(Date date) {
		final LocalDate local = LocalDate.of(date.getYear() + 1900, date.getMonth() + 1, date.getDate());
		return local;
	}

	private HttpHeaders setHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Auth-Schema", FabrickCostants.AUTH_SCHEMA);
		headers.set("X-Time-Zone", "Europe/Rome");
		headers.set("apikey", FabrickCostants.APY_KEY);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity(headers);
		return headers;
	}

	public RestTemplate getRestTemplate() {
		return this.restTemplate;
	}

	public void setRestTemplate(final RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
}
