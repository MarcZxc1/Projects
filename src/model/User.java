package model;

public class User extends Person {
	public User(String name, String email) {
		super(name, email);
	}

	@Override
	public void displayRole() {
		System.out.println("Role: User");
	}
}