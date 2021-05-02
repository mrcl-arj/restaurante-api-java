package com.marcelo.restaurante.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcelo.restaurante.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
