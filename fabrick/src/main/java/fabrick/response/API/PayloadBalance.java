package fabrick.response.API;

import org.codehaus.jackson.annotate.JsonProperty;

public class PayloadBalance {
	 @JsonProperty("date")
	 private String date;
	 @JsonProperty("balance")
	 private float balance;
	 @JsonProperty("availableBalance")
	 private float availableBalance;
	 @JsonProperty("currency")
	 private String currency;


	 // Getter Methods 

	 public String getDate() {
	  return date;
	 }

	 public float getBalance() {
	  return balance;
	 }

	 public float getAvailableBalance() {
	  return availableBalance;
	 }

	 public String getCurrency() {
	  return currency;
	 }

	 // Setter Methods 

	 public void setDate(String date) {
	  this.date = date;
	 }

	 public void setBalance(float balance) {
	  this.balance = balance;
	 }

	 public void setAvailableBalance(float availableBalance) {
	  this.availableBalance = availableBalance;
	 }

	 public void setCurrency(String currency) {
	  this.currency = currency;
	 }

}
