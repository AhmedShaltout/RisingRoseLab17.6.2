package classes;

public class Normal {
	private float ageFrom,ageTo,normalFrom,normalTo;
	private long testId,normalId;
	private short sex,ageType;
	private String comment;
	public Normal(float ageFrom, float ageTo, float normalFrom, float normalTo, long testId, long normalId, short sex,
			short ageType, String comemnt) {
		this.ageFrom = ageFrom;
		this.ageTo = ageTo;
		this.normalFrom = normalFrom;
		this.normalTo = normalTo;
		this.testId = testId;
		this.normalId = normalId;
		this.sex = sex;
		this.ageType = ageType;
		this.setComment(comemnt);
	}
	public Float getAgeFrom() {
		return this.ageFrom;
	}
	public void setAgeFrom(float ageFrom) {
		this.ageFrom = ageFrom;
	}
	public Float getAgeTo() {
		return this.ageTo;
	}
	public void setAgeTo(float ageTo) {
		this.ageTo = ageTo;
	}
	public Float getNormalFrom() {
		return this.normalFrom;
	}
	public void setNormalFrom(float normalFrom) {
		this.normalFrom = normalFrom;
	}
	public Float getNormalTo() {
		return this.normalTo;
	}
	public void setNormalTo(float normalTo) {
		this.normalTo = normalTo;
	}
	public Long getTestId() {
		return this.testId;
	}
	public void setTestId(long testId) {
		this.testId = testId;
	}
	public Long getNormalId() {
		return this.normalId;
	}
	public void setNormalId(long normalId) {
		this.normalId = normalId;
	}
	public short getSex() {
		return this.sex;
	}
	public void setSex(short sex) {
		this.sex = sex;
	}
	public short getAgeType() {
		return this.ageType;
	}
	public void setAgeType(short ageType) {
		this.ageType = ageType;
	}
	public String getComment() {
		return this.comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
