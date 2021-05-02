package com.marcelo.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcelo.restaurante.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
