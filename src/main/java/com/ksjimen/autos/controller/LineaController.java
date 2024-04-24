package com.ksjimen.autos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ksjimen.autos.dto.RequestLineaDto;
import com.ksjimen.autos.dto.ResponseLineaDto;
import com.ksjimen.autos.model.LineaVehiculo;
import com.ksjimen.autos.service.LineaService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/linea/")
public class LineaController {
	
	@Autowired
	private LineaService lineaService;
	
	@PostMapping(value = "/new")
	public ResponseEntity<ResponseLineaDto> agregar(@RequestBody RequestLineaDto lineaDto){
		return ResponseEntity.ok(lineaService.agregar(lineaDto));
		
	}
	
	@GetMapping(value="/lineaVehiculo")
	@CrossOrigin
	public List<LineaVehiculo> listar(){
		return lineaService.listar();
	}
	
	@GetMapping(value="/linea")
	@CrossOrigin
	public Optional<List<LineaVehiculo>> listar(@RequestParam Long idMarca){
		return lineaService.findById(idMarca);
	}

}
