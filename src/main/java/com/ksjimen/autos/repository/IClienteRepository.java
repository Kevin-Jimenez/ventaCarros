package com.ksjimen.autos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ksjimen.autos.model.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Long>{

}
