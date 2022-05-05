package de.lofi.carservice.exception;

public class CarAlreadyExistingException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CarAlreadyExistingException() {
		super();
	}

	public CarAlreadyExistingException(String arg0) {
		super(arg0);
	}

	public CarAlreadyExistingException(String arg0, java.lang.Throwable arg1) {
		super(arg0, arg1);
	}

}
