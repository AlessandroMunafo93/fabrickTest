package fabrick.response.API;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
public class PayloadTransactions {
	 @JsonProperty("list")
 private List<Transaction> list=new ArrayList<>();

public List<Transaction>  getList() {
	return list;
}

public void setList(List<Transaction>  list) {
	this.list = list;
}
}
