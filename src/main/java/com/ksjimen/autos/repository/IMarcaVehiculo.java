package com.ksjimen.autos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ksjimen.autos.model.MarcaVehiculo;

@Repository
public interface IMarcaVehiculo extends JpaRepository<MarcaVehiculo, Long>{

}
