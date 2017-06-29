package classes;

public class Patient {
	private int patientId;		private String patientName,age;
	private short ageType;
	public Patient(int patientId, String patientName,short ageType,String age) {
		this.patientId = patientId;
		this.patientName = patientName;
		this.ageType=ageType;
		this.age=age;
	}

	public Integer getPatientId() {
		return this.patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public short getAgeType() {
		return this.ageType;
	}

	public void setAgeType(short ageType) {
		this.ageType = ageType;
	}

	public String getAge() {
		return this.age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
}
