package model;

public class Construction extends Location{
	
	private String razonSocial;

	public Construction(String name, String address, String razonSocial) {
		super(name, address);
		this.razonSocial = razonSocial;
	}

}
