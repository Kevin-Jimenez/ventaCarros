package com.ksjimen.autos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ksjimen.autos.dto.RequestLineaDto;
import com.ksjimen.autos.dto.ResponseLineaDto;
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
		System.out.println("Controller IdMarca: "+lineaDto.getIdmarca()+ " Linea: "+lineaDto.getLinea());
		return ResponseEntity.ok(lineaService.agregar(lineaDto));
		
	}

}
