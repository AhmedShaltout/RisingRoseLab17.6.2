package classes;

public class Result {
	private String testName,testValue;	private int testId;

	public Result(String testValue, String testName, int testId) {
		this.testValue = testValue;
		this.testName = testName;
		this.testId = testId;
	}
	public String getTestValue() {
		return this.testValue;
	}

	public void setTestValue(String testValue) {
		this.testValue = testValue;
	}

	public String getTestName() {
		return this.testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public int getTestId() {
		return this.testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}
	@Override
	public String toString() {
		return "[testName=" + testName + ", testValue=" + testValue + "]";
	}
	
}
