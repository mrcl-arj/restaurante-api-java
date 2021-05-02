package com.marcelo.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcelo.restaurante.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
