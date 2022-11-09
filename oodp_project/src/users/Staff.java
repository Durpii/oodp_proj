package users;

public class Staff extends User{
	private String password;
	
	public Staff() {
		
	}
	
	public Staff(int userId, int age, String name) {
		this.userId = userId;
		this.age = age;
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}		

}
