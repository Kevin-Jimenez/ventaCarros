package com.ksjimen.autos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ksjimen.autos.dto.RequestUsuarioDto;
import com.ksjimen.autos.dto.ResponseUsuarioDto;
import com.ksjimen.autos.service.UsuarioService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/usuarios/")
public class UsuarioController {
	
	@Autowired
	private final UsuarioService usuService;
	
	@PostMapping(value = "new")
	public ResponseEntity<ResponseUsuarioDto> agregarUsuario(@RequestBody RequestUsuarioDto requestUsuario){
		return ResponseEntity.ok(usuService.agregar(requestUsuario));
	}
	
	@PostMapping(value = "edit")
	public ResponseEntity<ResponseUsuarioDto> actualizarUsuario(@RequestBody RequestUsuarioDto requestUsuario){
		return ResponseEntity.ok(usuService.actualizar(requestUsuario));
	}
}
