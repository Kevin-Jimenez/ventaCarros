package com.ksjimen.autos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ksjimen.autos.model.ImagenVehiculo;
import com.ksjimen.autos.model.Vehiculo;

public interface IImagenRepoSitory extends JpaRepository<ImagenVehiculo, Long>{
	@Query("SELECT i FROM ImagenVehiculo i WHERE i.vehiculo.idVehiculo = ?1")
    List<ImagenVehiculo> findImagenesByVehiculoId(Long idVehiculo);
}
