package fabrick.response.API;

import org.codehaus.jackson.annotate.JsonProperty;

public class Transaction {
	 @JsonProperty("transactionId")
	private String transactionId;
	 @JsonProperty("operationId")
	 private String operationId;
	 @JsonProperty("accountingDate")
	 private String accountingDate;
	 @JsonProperty("valueDate")
	 private String valueDate;
	 @JsonProperty("type")
	 private Type type;
	 @JsonProperty("amount")
	 private float amount;
	 @JsonProperty("currency")
	 private String currency;
	 @JsonProperty("description")
	 private String description;


	 // Getter Methods 

	 public String getTransactionId() {
	  return transactionId;
	 }

	 public String getOperationId() {
	  return operationId;
	 }

	 public String getAccountingDate() {
	  return accountingDate;
	 }

	 public String getValueDate() {
	  return valueDate;
	 }

	 public Type getType() {
	  return type;
	 }

	 public float getAmount() {
	  return amount;
	 }

	 public String getCurrency() {
	  return currency;
	 }

	 public String getDescription() {
	  return description;
	 }

	 // Setter Methods 

	 public void setTransactionId(String transactionId) {
	  this.transactionId = transactionId;
	 }

	 public void setOperationId(String operationId) {
	  this.operationId = operationId;
	 }

	 public void setAccountingDate(String accountingDate) {
	  this.accountingDate = accountingDate;
	 }

	 public void setValueDate(String valueDate) {
	  this.valueDate = valueDate;
	 }

	 public void setType(Type typeObject) {
	  this.type = type;
	 }

	 public void setAmount(float amount) {
	  this.amount = amount;
	 }

	 public void setCurrency(String currency) {
	  this.currency = currency;
	 }

	 public void setDescription(String description) {
	  this.description = description;
	 }
	}

