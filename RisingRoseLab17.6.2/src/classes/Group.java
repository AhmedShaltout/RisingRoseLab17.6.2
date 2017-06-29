package classes;

public class Group {
	private Integer groupId,tests;
	private String groupName,Edit,Delete;
	private Float groupPrice,hours;
	
	public Group(Integer groupId, Integer tests, String groupName, Float groupPrice, Float hours) {
		this.groupId = groupId;
		this.tests = tests;
		this.groupName = groupName;
		this.groupPrice = groupPrice;
		this.hours = hours;
		this.Edit="Edit";
		this.Delete="Delete";
	}
	
	public String getEdit() {
		return Edit;
	}
	
	public String getDelete() {
		return Delete;
	}
	
	public Integer getGroupId() {
		return this.groupId;
	}
	
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
	public Integer getTests() {
		return this.tests;
	}
	
	public void setTests(Integer tests) {
		this.tests = tests;
	}
	
	public String getGroupName() {
		return this.groupName;
	}
	
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public Float getGroupPrice() {
		return this.groupPrice;
	}
	
	public void setGroupPrice(Float groupPrice) {
		this.groupPrice = groupPrice;
	}
	
	public Float getHours() {
		return this.hours;
	}
	
	public void setHours(Float hours) {
		this.hours = hours;
	}

	@Override
	public boolean equals(Object obj) {
		Group test=(Group) obj;
		try{
			return this.groupId==test.getGroupId();
		}catch(Exception e){
			return this.groupName.equals(test.getGroupName());
		}
	}
	
	
}
