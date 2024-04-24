package com.ksjimen.autos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ksjimen.autos.model.LineaVehiculo;
import com.ksjimen.autos.model.MarcaVehiculo;

public interface ILineaRepository extends JpaRepository<LineaVehiculo, Long>{
	
	Optional<LineaVehiculo> findByIdLineaAndIdMarca(Long idLinea, MarcaVehiculo idMarca);
	
	Optional<List<LineaVehiculo>> findByIdMarca(Optional<MarcaVehiculo> marca);

}
