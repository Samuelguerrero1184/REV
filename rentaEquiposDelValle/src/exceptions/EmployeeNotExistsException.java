package exceptions;

public class EmployeeNotExistsException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public EmployeeNotExistsException() {
		super("This employee don't exists into the data base");
	}
	
}