package model;

public class Client extends Person{
	
	private String address;
	private String phone;

	/**
	 * 
	 */
	private static final long serialVersionUID = -6195970929649304766L;

	public Client(String name, String lastname, int id) {
		super(name, lastname, id);
		
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
