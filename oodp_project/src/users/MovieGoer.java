package users;

public class MovieGoer extends User{
	int phoneNumber;
	String email;
	
	public MovieGoer() {
		
	}
	
	public MovieGoer(int userId, int age, String name, int phoneNumber, String email) {
		this.userId = userId;
		this.age = age;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
