package com.ksjimen.autos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseImagenDto {
	private Long idImagen;
    private String rutaImagen;
}
