package com.ksjimen.autos.dto;
import java.time.Year;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestVehiculoDto {
	private Long idVehiculo;
	private Long idMarca;
	private Long idLinea;
	private Long idTVehiculo;
	private Year modelo;
	private String cilindraje;
	private String kilometraje;
	private String descripcion;
	//private String estado;
	private double precio;
	private double precio_venta;
	private Date fecha_venta;
}
