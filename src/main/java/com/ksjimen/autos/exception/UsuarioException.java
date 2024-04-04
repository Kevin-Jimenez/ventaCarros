package com.ksjimen.autos.exception;

public class UsuarioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioException(String message) {
        super(message);
    }

    public UsuarioException(String message, Throwable cause) {
        super(message, cause);
    }

}
