package com.ksjimen.autos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ksjimen.autos.dto.VehiculosDTO;
import com.ksjimen.autos.model.Vehiculo;
import com.ksjimen.autos.service.VehiculoService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/")
public class IndexController {
	
	@Autowired
	private VehiculoService vehiculoService;
	
	
	@GetMapping
	public ResponseEntity<List<VehiculosDTO>> welcome() {
		return ResponseEntity.ok(vehiculoService.listar());
	}
	
	/*@GetMapping(value = "paginado")
	public Page<Vehiculo> getAll(Pageable pageable){
		return vehiculoService.getAll(pageable);
	}*/

}
