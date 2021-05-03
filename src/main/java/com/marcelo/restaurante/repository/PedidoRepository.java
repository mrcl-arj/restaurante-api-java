package com.marcelo.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcelo.restaurante.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}
