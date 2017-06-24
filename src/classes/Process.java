package classes;

public class Process {
	private int processId;	private String processInDate,processOutDate,processPrint="Print",processDetails="Check",processPay="Pay";
	private float processPrice,processPaid;

	public Process(int processId, String processInDate, String processOutDate,float processPrice,float processPaid) {
		this.processId = processId;
		this.processInDate = processInDate;
		this.processOutDate = processOutDate;
		this.processPrice = processPrice;
		this.processPaid=processPaid;
	}


	public float getProcessPrice() {
		return processPrice;
	}


	public void setProcessPrice(float processPrice) {
		this.processPrice = processPrice;
	}


	public Integer getProcessId() {
		return processId;
	}


	public void setProcessId(int processId) {
		this.processId = processId;
	}

	public String getProcessInDate() {
		return processInDate;
	}


	public void setProcessInDate(String processInDate) {
		this.processInDate = processInDate;
	}


	public String getProcessOutDate() {
		return processOutDate;
	}


	public void setProcessOutDate(String processOutDate) {
		this.processOutDate = processOutDate;
	}


	public String getProcessPrint() {
		return processPrint;
	}


	public void setProcessPrint(String processPrint) {
		this.processPrint = processPrint;
	}


	public String getProcessDetails() {
		return processDetails;
	}


	public void setProcessDetails(String processDetails) {
		this.processDetails = processDetails;
	}


	public float getProcessPaid() {
		return processPaid;
	}


	public void setProcessPaid(float processPaid) {
		this.processPaid = processPaid;
	}


	public String getProcessPay() {
		return this.processPay;
	}


	public void setProcessPay(String processPay) {
		this.processPay = processPay;
	}
	
	
}
