package users;

public abstract class User {
	protected int userId;
	protected int age;
	protected String name;
	
	public User() {
		
	}
	
	public User(int userId, int age, String name) {
		this.userId = userId;
		this.age = age;
		this.name = name;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
