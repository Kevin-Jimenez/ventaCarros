package com.ksjimen.autos.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksjimen.autos.dto.RequestTVehiculoDto;
import com.ksjimen.autos.dto.ResponseTVehiculoDto;
import com.ksjimen.autos.model.TipoVehiculo;
import com.ksjimen.autos.repository.ITipoVRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TipoVService {
	
	@Autowired
	private ITipoVRepository tVRepository;
	
	public ResponseTVehiculoDto agregar(RequestTVehiculoDto request) {
		TipoVehiculo vehiculo = TipoVehiculo.builder()
				.tipo(request.getTipo_vehiculo())
				.fecha_registro(new Date())
				.fecha_modificacion(new Date())
				.estado_registro("A00S")
				.usuario_adicion(0)
				.usuario_modificacion(0)
				.build();
		TipoVehiculo response = tVRepository.save(vehiculo);
		
		return ResponseTVehiculoDto.builder()
				.response(response.getTipo()).build();
	}
	

}
