package com.ksjimen.autos.dto;

import java.time.Year;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVehiculoDto {
	private String respuesta;
	private Year modelo;
}
