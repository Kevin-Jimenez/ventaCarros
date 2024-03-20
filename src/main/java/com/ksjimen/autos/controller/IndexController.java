package com.ksjimen.autos.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/v1/")
public class IndexController {
	
	@PostMapping
	public String welcome() {
		return "Bienvenido al aplicativo Autos Koach";
	}

}
