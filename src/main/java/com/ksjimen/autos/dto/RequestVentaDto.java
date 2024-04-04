package com.ksjimen.autos.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestVentaDto {
	private Long idVenta;
	private String descripcion;
	private double valorVenta; 
	private RequestClienteDto cliente;
	private RequestVehiculoDto vehiculo;
	private RequestUsuarioDto usuario;
}
