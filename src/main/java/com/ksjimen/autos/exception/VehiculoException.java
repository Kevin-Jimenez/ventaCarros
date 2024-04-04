package com.ksjimen.autos.exception;

public class VehiculoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VehiculoException(String message) {
        super(message);
    }

    public VehiculoException(String message, Throwable cause) {
        super(message, cause);
    }

}
