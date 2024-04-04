package com.ksjimen.autos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestClienteDto {
	private Long idCliente;
	private String tipoIdentificacion;
	private String numeroIdentificacion;
	private String nombre;
	private String apellido;
}
