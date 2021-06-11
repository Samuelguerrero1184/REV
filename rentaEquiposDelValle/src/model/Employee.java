package model;

import java.time.LocalDate;

public class Employee extends Person {

	private LocalDate birthDay;
	private String shirtSize;
	private String pantsSize;
	private String shoesSize;
	private LocalDate entryDate;

	/**
	 * 
	 */
	private static final long serialVersionUID = 2246750831096443988L;

	public Employee(String name, String lastname, String id, LocalDate bDay, String shirtSize, String pantsSize, String shoesSize, LocalDate entryDate) {
		super(name, lastname, id);
		this.birthDay = bDay;
		this.shirtSize = shirtSize;
		this.pantsSize = pantsSize;
		this.shoesSize = shoesSize;
		this.entryDate = entryDate;
	}

	public LocalDate getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
	}

	public String getShirtSize() {
		return shirtSize;
	}

	public void setShirtSize(String shirtSize) {
		this.shirtSize = shirtSize;
	}

	public String getPantsSize() {
		return pantsSize;
	}

	public void setPantsSize(String pantsSize) {
		this.pantsSize = pantsSize;
	}

	public String getShoesSize() {
		return shoesSize;
	}

	public void setShoesSize(String shoesSize) {
		this.shoesSize = shoesSize;
	}

	public LocalDate getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(LocalDate entryDate) {
		this.entryDate = entryDate;
	}
}
