package com.ksjimen.autos.utils;

public enum EstadosVehiculo {
	DISPONIBLE(1),
	VENDIDO(1);
	
	private final int valor;
	
	EstadosVehiculo(int valor) {
		this.valor = valor;
	}
	
	public int getValor() {
        return valor;
    }

}
