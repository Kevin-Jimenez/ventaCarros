package com.ksjimen.autos.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ksjimen.autos.service.ImagenVehiculoService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/imagenes/")
public class ImagenVehiculoController {
	
	@Autowired
	private ImagenVehiculoService imagenService;
	
	@PostMapping("/{idVehiculo}")
    public ResponseEntity<String> guardarImagen(@RequestParam("file") List<MultipartFile> files, @PathVariable Long idVehiculo) {
        try {
        	imagenService.guardarImagen(files, idVehiculo);
            return ResponseEntity.ok("Imagen guardada correctamente");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la imagen");
        }
    }

}
