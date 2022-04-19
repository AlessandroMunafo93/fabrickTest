package fabrick.request;

import java.util.Date;

public class TransactionRequest extends BaseRequest {
	/**
		 * 
		 */
	private static final long serialVersionUID = 3317612300643105760L;
	private Date fromAccountingDate;
	private Date toAccountingDate;
	public Date getFromAccountingDate() {
		return fromAccountingDate;
	}
	public void setFromAccountingDate(Date fromAccountingDate) {
		this.fromAccountingDate = fromAccountingDate;
	}
	public Date getToAccountingDate() {
		return toAccountingDate;
	}
	public void setToAccountingDate(Date toAccountingDate) {
		this.toAccountingDate = toAccountingDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
