package com.ksjimen.autos.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksjimen.autos.dto.RequestLineaDto;
import com.ksjimen.autos.dto.ResponseLineaDto;
import com.ksjimen.autos.model.LineaVehiculo;
import com.ksjimen.autos.model.MarcaVehiculo;
import com.ksjimen.autos.repository.ILineaRepository;
import com.ksjimen.autos.repository.IMarcaVehiculo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LineaService {

	@Autowired
	private ILineaRepository linearepo;
	private IMarcaVehiculo marcaVehiculo;
	
	public ResponseLineaDto agregar(RequestLineaDto lineaDto) {
		Optional<MarcaVehiculo> marca = marcaVehiculo.findById(lineaDto.getIdmarca());
		if(marca.isEmpty()) {
			return ResponseLineaDto.builder().linea("")
					.marca("").response("No existe una marca para la linea que desa agregar").build();
		}
		MarcaVehiculo marcaV = marca.get();
		LineaVehiculo linea = LineaVehiculo.builder()
				.linea(lineaDto.getLinea()).estado_registro("A00S")
				.fecha_registro(new Date()).fecha_modificacion(new Date())
				.idMarca(marcaV).build();
		
		LineaVehiculo response = linearepo.save(linea);
		
		return ResponseLineaDto.builder()
				.linea(response.getLinea()).marca(marcaV.getMarca())
				.response("Se agrego la marca de forma correcta.").build();
	}

}
