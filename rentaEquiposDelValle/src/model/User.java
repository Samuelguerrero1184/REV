package model;

public class User extends Person{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4242390660584561513L;
	private String userPassword;
	private String userName;

	
	public User(String name, String lastname, int id, String userName, String password) {
		super(name, lastname, id);
		this.userName = userName;
		this.userPassword = password;
	}

	public String getUserPassword() {
		return userPassword;
	}


	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}



}
