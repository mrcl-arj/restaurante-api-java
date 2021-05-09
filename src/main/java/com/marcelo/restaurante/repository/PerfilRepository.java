package com.marcelo.restaurante.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcelo.restaurante.enums.PerfilNome;
import com.marcelo.restaurante.model.Perfil;

public interface PerfilRepository extends JpaRepository<Perfil, Long>{
	Optional<Perfil> findByNome(PerfilNome perfil);
}
