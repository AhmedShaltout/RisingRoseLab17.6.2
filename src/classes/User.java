package classes;

public class User {

	private int id;
	private short role;
	private String password, name;
	
	public User(int id,String name,short role,String password){
		this.id=id;this.name=name;this.role=role;this.password=password;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public short getRole() {
		return this.role;
	}

	public void setRole(short role) {
		this.role = role;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
