package com.ksjimen.autos.exception;

public class ValidacionRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValidacionRequestException(String message) {
        super(message);
    }

    public ValidacionRequestException(String message, Throwable cause) {
        super(message, cause);
    }

}
