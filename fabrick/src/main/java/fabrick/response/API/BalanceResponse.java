package fabrick.response.API;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class BalanceResponse {
	@JsonProperty("status")
	private String status;
	@JsonProperty("error")
	private String[] error;
	@JsonProperty("payload")
	private PayloadBalance payload;

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

	public PayloadBalance getPayloadBalance() {
		return payload;
	}

	public void setPayload(PayloadBalance payload) {
		this.payload = payload;
	}

}