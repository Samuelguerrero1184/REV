package model;

public class User extends Person{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4242390660584561513L;
	private String userPassword;
	private String userName;
	private User left;
	private User right;
	private User father;

	
	public User(String name, String lastname, String id, String userName, String password) {
		super(name, lastname, id);
		this.userName = userName;
		this.userPassword = password;
		this.father=null;
		this.left=null;
		this.right=null;
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

	public User getLeft() {
		return left;
	}

	public void setLeft(User left) {
		this.left = left;
	}

	public User getRight() {
		return right;
	}

	public void setRight(User right) {
		this.right = right;
	}

	public User getFather() {
		return father;
	}

	public void setFather(User father) {
		this.father = father;
	}



}
