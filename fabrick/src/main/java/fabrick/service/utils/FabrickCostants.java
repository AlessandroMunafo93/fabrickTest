package fabrick.service.utils;

public class FabrickCostants {
	public static final String AUTH_SCHEMA = "S2S";
	public static final String APY_KEY = "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP";
	public static final String TRANSACTIONS_URL = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/{accountId}/transactions?fromAccountingDate={fromAccountingDate}&toAccountingDate={toAccountingDate}";
	public static final String BALANCE_URL = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/{accountId}/balance";
	public static final String BANK_TRANSFER ="https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/{accountId}/payments/money-transfers";
	public static final String DESCRIPTION = "Operazione andata a buon fine";
}
