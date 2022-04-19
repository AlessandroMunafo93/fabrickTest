package fabrick.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import fabrick.request.BankTransferRequest;
import fabrick.request.BaseRequest;
import fabrick.request.TransactionRequest;
import fabrick.response.AccountBalanceResponse;
import fabrick.response.BankTransferResponse;
import fabrick.response.API.Transaction;
import fabrick.service.impl.FabrickServiceBean;

/**
 * @author almunafo
 *
 */
@RestController
public class FabrickService {
	public FabrickServiceBean fabrickServiceBean;

	@RequestMapping(value = "/balance", method = RequestMethod.POST, headers = "Accept=application/json")
	public AccountBalanceResponse getBalance(@RequestBody BaseRequest request)
			throws JsonParseException, JsonMappingException, IOException {
		return fabrickServiceBean.getBalance(request);

	}

	@RequestMapping(value = "/bankTransfer", method = RequestMethod.POST, headers = "Accept=application/json")
	public BankTransferResponse bankTransfer(@RequestBody BankTransferRequest request) {
		return fabrickServiceBean.bankTransfer(request);

	}

	@RequestMapping(value = "/transactions", method = RequestMethod.POST, headers = "Accept=application/json")
	public List<Transaction> getTransactions(@RequestBody TransactionRequest request)
			throws JsonParseException, JsonMappingException, IOException {
		return fabrickServiceBean.getTransactions(request);

	}

	public FabrickService() {
		super();
	}

	public FabrickServiceBean getFabrickServiceBean() {
		return fabrickServiceBean;
	}

	public void setFabrickServiceBean(FabrickServiceBean fabrickServiceBean) {
		this.fabrickServiceBean = fabrickServiceBean;
	}

}
