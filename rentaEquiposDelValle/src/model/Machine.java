package model;

public class Machine extends Product{

	private String typeGasoline;
	private String typeMachine;
	
	
	public Machine(String name, String brand, float serial, int internalNumber, String typeGasoline, String typeMachine) {
		super(name, brand, serial, internalNumber);
		this.typeGasoline = typeGasoline;
		this.typeMachine = typeMachine;
	}


	public String getTypeGasoline() {
		return typeGasoline;
	}


	public void setTypeGasoline(String typeGasoline) {
		this.typeGasoline = typeGasoline;
	}


	public String getTypeMachine() {
		return typeMachine;
	}


	public void setTypeMachine(String typeMachine) {
		this.typeMachine = typeMachine;
	}
	
}
