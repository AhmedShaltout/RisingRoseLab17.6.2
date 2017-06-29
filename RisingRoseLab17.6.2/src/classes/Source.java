package classes;

public class Source {
	private String name,delete="Delete",edit="Edit";	private int Id;		private float discount;


	public Source(String name, int id, float discount) {
		this.name = name;
		this.Id = id;
		this.discount = discount;
	}


	public String getName() {
		return this.name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getId() {
		return this.Id;
	}


	public void setId(int id) {
		this.Id = id;
	}


	public Float getDiscount() {
		return this.discount;
	}


	public void setDiscount(float discount) {
		this.discount = discount;
	}


	public String getEdit() {
		return this.edit;
	}


	public void setEdit(String edit) {
		this.edit = edit;
	}


	public String getDelete() {
		return this.delete;
	}


	public void setDelete(String delete) {
		this.delete = delete;
	}
	
	
}
