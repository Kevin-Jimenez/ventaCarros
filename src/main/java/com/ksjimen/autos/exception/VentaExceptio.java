package com.ksjimen.autos.exception;

public class VentaExceptio extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VentaExceptio(String message) {
        super(message);
    }

    public VentaExceptio(String message, Throwable cause) {
        super(message, cause);
    }

}
