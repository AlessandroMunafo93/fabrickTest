package fabrick.response.API;

import org.codehaus.jackson.annotate.JsonProperty;

public class Type {
	 @JsonProperty("enumeration")
	 private String enumeration;
	 @JsonProperty("value")
	 private String value;


	 // Getter Methods 

	 public String getEnumeration() {
	  return enumeration;
	 }

	 public String getValue() {
	  return value;
	 }

	 // Setter Methods 

	 public void setEnumeration(String enumeration) {
	  this.enumeration = enumeration;
	 }

	 public void setValue(String value) {
	  this.value = value;
	 }
}