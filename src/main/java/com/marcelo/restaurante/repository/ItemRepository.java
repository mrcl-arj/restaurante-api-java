package com.marcelo.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcelo.restaurante.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

}
