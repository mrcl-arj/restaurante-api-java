package com.marcelo.restaurante.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.marcelo.restaurante.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	
	@Query(value = "SELECT *  FROM pedido where status='FINALIZADO'", nativeQuery=true)
    List<Pedido> pedidosFinalizados();

}
