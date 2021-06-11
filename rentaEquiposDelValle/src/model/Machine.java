package model;

public class Machine extends Product{

	private String typeGasoline;
	private String typeMachine;
	private Machine left;
	private Machine right;
	private Machine father;
	
	
	public Machine(String typeMachine, String brand,String name , int internalNumber, String serial, String typeGasoline) {
		super(name, brand, serial, internalNumber);
		this.typeGasoline = typeGasoline;
		this.typeMachine = typeMachine;
		this.left = null;
		this.right = null;
		this.father = null;
		
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


	public Machine getLeft() {
		return left;
	}


	public void setLeft(Machine left) {
		this.left = left;
	}


	public Machine getRight() {
		return right;
	}


	public void setRight(Machine right) {
		this.right = right;
	}


	public Machine getFather() {
		return father;
	}


	public void setFather(Machine father) {
		this.father = father;
	}
	
}
