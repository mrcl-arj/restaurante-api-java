package com.marcelo.restaurante.repository;


import com.marcelo.restaurante.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    List<Usuario> findAll();

    Usuario findByEmail(String email);

    Usuario findUserByEmail(String email);
}
