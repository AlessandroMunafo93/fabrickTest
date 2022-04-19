package fabrick.response.API;

import org.codehaus.jackson.annotate.JsonProperty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrasanctionsResponse {
	@JsonProperty("status")
	private String status;
	@JsonProperty("error")
	private String[] error;
	@JsonProperty("payload")
	private PayloadTransactions payload;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String[] getError() {
		return error;
	}

	public void setError(String[] error) {
		this.error = error;
	}

	public PayloadTransactions getPayloadTransactions() {
		return payload;
	}

	public void setPayloadTransactions(PayloadTransactions payload) {
		this.payload = payload;
	}

}
