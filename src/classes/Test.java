package classes;

public class Test {
	private Long testId;	private String name,comment;	private float hoursTaken,price;		
	public String Delete,Edit;

	public Test(long testId, String name, String comment, float hoursTaken,float price) {
		this.name = name;
		this.comment = comment;
		this.hoursTaken = hoursTaken;
		this.testId=testId;
		this.price=price;
		this.Delete="Delete";
		this.Edit="Edit";
	}


	public String getName() {
		return this.name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getComment() {
		return this.comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public Float getHoursTaken() {
		return this.hoursTaken;
	}


	public void setHoursTaken(float hoursTaken) {
		this.hoursTaken = hoursTaken;
	}


	public Long getTestId() {
		return this.testId;
	}


	public void setTestId(long testId) {
		this.testId = testId;
	}


	public Float getPrice() {
		return this.price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public String getDelete() {
		return this.Delete;
	}


	public String getEdit() {
		return this.Edit;
	}


	@Override
	public boolean equals(Object obj) {
		Test test=(Test) obj;
		try{
			return this.testId==test.getTestId();
		}catch(Exception e){
			try{
				return this.name.equals(test.getName());
			}catch(Exception ex){
				return false;
			}
		}
	}
	
}
