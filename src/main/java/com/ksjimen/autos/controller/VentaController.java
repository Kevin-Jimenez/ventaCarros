package com.ksjimen.autos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ksjimen.autos.dto.RequestVentaDto;
import com.ksjimen.autos.dto.ResponseVentaDto;
import com.ksjimen.autos.service.VentaService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/venta/")
public class VentaController {
	
	@Autowired
	private VentaService ventaService;
	
	@PostMapping(value = "new")
	public ResponseEntity<ResponseVentaDto> registrarVenta(@RequestBody RequestVentaDto requestVenta){
		return ResponseEntity.ok(ventaService.registrarVenta(requestVenta));
	}

}
