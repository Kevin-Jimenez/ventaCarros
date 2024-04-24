package com.ksjimen.autos.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksjimen.autos.dto.RequestMarcaDto;
import com.ksjimen.autos.dto.ResponseMarcaDto;
import com.ksjimen.autos.model.MarcaVehiculo;
import com.ksjimen.autos.repository.IMarcaVehiculo;
import com.ksjimen.autos.repository.IVehiculoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MarcaService {
	
	@Autowired
	private IMarcaVehiculo IvehiculoMarca;

	public ResponseMarcaDto agregar(RequestMarcaDto marcaDto) {
		MarcaVehiculo marcaV = MarcaVehiculo.builder()
				.marca(marcaDto.getMarca())
				.estado_registro("A00S")
				.fecha_registro(new Date())
				.fecha_modificacion(new Date())
				.usuario_adicion(0)
				.usuario_modificacion(0)
				.build();
	
		MarcaVehiculo response = IvehiculoMarca.save(marcaV);
		
		return ResponseMarcaDto.builder()
				.marca(response.getMarca())
				.response("¡Marca Registrada Exitosamente!")
				.build();
	}

	public List<MarcaVehiculo> listar() {
		List<MarcaVehiculo> marcas = IvehiculoMarca.findAll();
		return marcas;
	}

}
