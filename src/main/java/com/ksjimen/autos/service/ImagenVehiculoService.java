package com.ksjimen.autos.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ksjimen.autos.exception.VehiculoException;
import com.ksjimen.autos.model.ImagenVehiculo;
import com.ksjimen.autos.model.Vehiculo;
import com.ksjimen.autos.repository.IImagenRepoSitory;
import com.ksjimen.autos.repository.IVehiculoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ImagenVehiculoService {
	
    private final String RUTA_ALMACENAMIENTO ="C:/xampp/htdocs/ejemploFrontAutos/img/";
    
    @Autowired
	private IImagenRepoSitory imagenRepo;
	private IVehiculoRepository vehiculoRepo;
	
	
	public void guardarImagen(List<MultipartFile> files, Long idVehiculo) throws IOException {
        String rutaDirectorio = RUTA_ALMACENAMIENTO + idVehiculo + "_vehiculo/";
        String rutaImagen;
        File directorio = new File(rutaDirectorio);
        if (!directorio.exists()) {
            directorio.mkdirs();
        }
        
        for (MultipartFile file : files) {
        	String nombreImagen = file.getOriginalFilename();
            Path rutaCompleta = Paths.get(rutaDirectorio + nombreImagen);
            Files.write(rutaCompleta, file.getBytes());
            
            Vehiculo vehiculo = vehiculoRepo.findById(idVehiculo)
            		.orElseThrow(() -> new VehiculoException("El vehiculo no existe"));
            rutaImagen = "/"+idVehiculo+"_vehiculo/"+nombreImagen;
            ImagenVehiculo imagen = ImagenVehiculo.builder()
            		.vehiculo(vehiculo)
            		.rutaImagen(rutaImagen.toString())
            		.build();
            imagenRepo.save(imagen);
        }
       
    }
}

