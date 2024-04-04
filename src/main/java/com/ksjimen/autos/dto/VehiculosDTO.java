package com.ksjimen.autos.dto;

import java.time.Year;
import java.util.List;

import com.ksjimen.autos.model.ImagenVehiculo;
import com.ksjimen.autos.model.LineaVehiculo;
import com.ksjimen.autos.model.MarcaVehiculo;
import com.ksjimen.autos.model.TipoVehiculo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehiculosDTO {
	private Long idVehiculo;
	private String cilindraje;
	private String kilometraje;
	private Year modelo;
	private String descripcion;
	private double precio;
	private MarcaVehiculo marca;
	private LineaVehiculo linea;
	private TipoVehiculo tipoVehiculo;
	private List<ResponseImagenDto> imagen;
	
}
