package fabrick.request;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JacksonAnnotation;
import org.codehaus.jackson.annotate.JsonProperty;

import com.fasterxml.jackson.annotation.JsonInclude;


public class BaseRequest implements Serializable {
	/**
		 * 
		 */
	public BaseRequest(){
		
		super();
	}
	private static final long serialVersionUID = 1L;
	private  long accountId;

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

}
