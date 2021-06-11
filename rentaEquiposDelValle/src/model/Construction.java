package model;

public class Construction extends Location{
	
	private String razonSocial;

	public Construction(String name, String address, String razonSocial) {
		super(name, address);
		this.setRazonSocial(razonSocial);
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

}
