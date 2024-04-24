package com.ksjimen.autos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ksjimen.autos.dto.RequestTVehiculoDto;
import com.ksjimen.autos.dto.ResponseTVehiculoDto;
import com.ksjimen.autos.model.TipoVehiculo;
import com.ksjimen.autos.service.TipoVService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/tipovehiculo/")
public class TipoVehiculoController {
	
	@Autowired
	private TipoVService tVService;
	
	@PostMapping(value = "/new")
	public ResponseEntity<ResponseTVehiculoDto> agregar(@RequestBody RequestTVehiculoDto rVehiculo){
		return ResponseEntity.ok(tVService.agregar(rVehiculo));
	}
	
	@GetMapping(value = "/tipo")
	@CrossOrigin
	public List<TipoVehiculo> listar(){
		return tVService.listar();
	}

}
