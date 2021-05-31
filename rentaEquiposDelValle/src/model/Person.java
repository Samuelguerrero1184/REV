package model;

import java.io.Serializable;

public abstract class Person implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String lastname;
	private int id;
	
public Person (String name, String lastname,int id){
	this.name = name;
	this.lastname = lastname;
	this.id = id;
}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
		public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
}
