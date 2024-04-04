package com.ksjimen.autos.exception;

public class LineaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LineaException(String message) {
        super(message);
    }

    public LineaException(String message, Throwable cause) {
        super(message, cause);
    }

}

