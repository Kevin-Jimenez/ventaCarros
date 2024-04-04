package com.ksjimen.autos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ksjimen.autos.model.Vehiculo;

public interface IVehiculoRepository extends JpaRepository<Vehiculo, Long>{
	@Query("SELECT v FROM Vehiculo v WHERE v.estado_registro = 'A00S' AND v.id_estado = 'DISPONIBLE'"
			+ "ORDER BY v.fecha_registro DESC")
    List<Vehiculo> findAllByEstadoRegistroAndIdEstado();
}
