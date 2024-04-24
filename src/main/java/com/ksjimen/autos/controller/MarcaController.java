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

import com.ksjimen.autos.dto.RequestMarcaDto;
import com.ksjimen.autos.dto.ResponseMarcaDto;
import com.ksjimen.autos.model.MarcaVehiculo;
import com.ksjimen.autos.service.MarcaService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/marca/")
public class MarcaController {
	
	@Autowired
	private MarcaService marcaService;
	
	@PostMapping(value = "new")
	public ResponseEntity<ResponseMarcaDto> agregar(@RequestBody RequestMarcaDto marcaDto){
		return ResponseEntity.ok(marcaService.agregar(marcaDto));
	}
	
	@GetMapping(value = "/marcaVehiculo")
	@CrossOrigin
	public List<MarcaVehiculo> listar(){
		return marcaService.listar();
	}

}
