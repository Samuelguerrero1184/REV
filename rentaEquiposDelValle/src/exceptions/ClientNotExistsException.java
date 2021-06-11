package exceptions;

public class ClientNotExistsException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClientNotExistsException() {
		super("This client don't exists into the data base");
	}

}

