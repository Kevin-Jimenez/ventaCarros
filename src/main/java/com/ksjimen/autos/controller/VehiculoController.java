package com.ksjimen.autos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ksjimen.autos.dto.RequestVehiculoDto;
import com.ksjimen.autos.dto.ResponseVehiculoDto;
import com.ksjimen.autos.service.VehiculoService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/vehiculo/")
public class VehiculoController {
	
	@Autowired
	private VehiculoService vehiculoService;
	
	@PostMapping(value = "/new")
	public ResponseEntity<ResponseVehiculoDto> agregar(@RequestBody RequestVehiculoDto requestVehiculo){
		return ResponseEntity.ok(vehiculoService.agregar(requestVehiculo));
	}

}
