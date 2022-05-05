package de.lofi.carservice.exception;

public class CarNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CarNotFoundException() {
		super();
	}

	public CarNotFoundException(String arg0) {
		super(arg0);
	}

	public CarNotFoundException(String arg0, java.lang.Throwable arg1) {
		super(arg0, arg1);
	}

}
