package com.ksjimen.autos.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksjimen.autos.dto.RequestVehiculoDto;
import com.ksjimen.autos.dto.ResponseVehiculoDto;
import com.ksjimen.autos.model.LineaVehiculo;
import com.ksjimen.autos.model.MarcaVehiculo;
import com.ksjimen.autos.model.Vehiculo;
import com.ksjimen.autos.repository.ILineaRepository;
import com.ksjimen.autos.repository.IMarcaVehiculo;
import com.ksjimen.autos.repository.IVehiculoRepository;
import com.ksjimen.autos.utils.EstadosVehiculo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VehiculoService {
	
	@Autowired
	private IVehiculoRepository vehiculoRepository;
	@Autowired
	private IMarcaVehiculo marcaRepository;
	@Autowired
	private ILineaRepository lineaRepository;
	
	public ResponseVehiculoDto agregar(RequestVehiculoDto requestveiculo) {
		Vehiculo respuesta;
		Optional<MarcaVehiculo> marcaVehiculo = marcaRepository.findById(requestveiculo.getIdMarca());
		Optional<LineaVehiculo> lineaVehiculo = lineaRepository.findByIdLineaAndIdMarca(requestveiculo.getIdLinea(), marcaVehiculo.get());
		
		if(!marcaVehiculo.isEmpty() && marcaVehiculo.isPresent()) {
			if(!lineaVehiculo.isEmpty() && lineaVehiculo.isPresent()) {
				Vehiculo vehiculo = Vehiculo.builder().cilindraje(requestveiculo.getCilindraje())
						.kilometraje(requestveiculo.getKilometraje()).modelo(requestveiculo.getModelo())
						.descripcion(requestveiculo.getDescripcion()).fecha_venta(requestveiculo.getFecha_venta())
						.id_estado(EstadosVehiculo.DISPONIBLE).precio(requestveiculo.getPrecio())
						.precio_venta(requestveiculo.getPrecio_venta()).fecha_registro(new Date()).fecha_modificacion(new Date())
						.usuario_adicion(0).usuario_modificacion(0).estado_registro("A00S").build();
				
				respuesta = vehiculoRepository.save(vehiculo);
				return ResponseVehiculoDto.builder()
						.respuesta("Registro creado Exitosamente.")
						.modelo(respuesta.getModelo())
						.build();
			}else {
				return ResponseVehiculoDto.builder()
						.respuesta("No existe marca asociada a la linea de vehiculo")
						.modelo(null).build();
			}
		}
		return ResponseVehiculoDto.builder()
				.respuesta("No existe marca")
				.modelo(null).build();
	}

}
