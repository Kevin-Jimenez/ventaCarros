package com.ksjimen.autos.exception;

public class TipoVehiculoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TipoVehiculoException(String message) {
        super(message);
    }

    public TipoVehiculoException(String message, Throwable cause) {
        super(message, cause);
    }

}