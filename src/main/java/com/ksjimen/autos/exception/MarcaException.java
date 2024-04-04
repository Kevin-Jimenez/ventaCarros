package com.ksjimen.autos.exception;

public class MarcaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MarcaException(String message) {
        super(message);
    }

    public MarcaException(String message, Throwable cause) {
        super(message, cause);
    }

}
