package fabrick.request.API;

public class LegalPersonBeneficiary {
	private String fiscalCode;
	private String legalRepresentativeFiscalCode;

	// Getter Methods

	public String getFiscalCode() {
		return fiscalCode;
	}

	public String getLegalRepresentativeFiscalCode() {
		return legalRepresentativeFiscalCode;
	}

	// Setter Methods

	public void setFiscalCode(String fiscalCode) {
		this.fiscalCode = fiscalCode;
	}

	public void setLegalRepresentativeFiscalCode(String legalRepresentativeFiscalCode) {
		this.legalRepresentativeFiscalCode = legalRepresentativeFiscalCode;
	}
}
