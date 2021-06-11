package model;

public class Product {

	private String name;
	private String brand;
	private String serial;
	private int internalNumber;
	
	
	public Product(String name, String brand, String serial, int internalNumber) {
		this.name = name;
		this.brand = brand;
		this.serial = serial;
		this.internalNumber = internalNumber;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getBrand() {
		return brand;
	}


	public void setBrand(String brand) {
		this.brand = brand;
	}


	public String getSerial() {
		return serial;
	}


	public void setSerial(String serial) {
		this.serial = serial;
	}


	public int getInternalNumber() {
		return internalNumber;
	}


	public void setInternalNumber(int internalNumber) {
		this.internalNumber = internalNumber;
	}
	
	
	
	
}

