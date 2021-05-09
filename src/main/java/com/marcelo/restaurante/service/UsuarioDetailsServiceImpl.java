package com.marcelo.restaurante.service;

import com.marcelo.restaurante.DTO.UsuarioDTO;
import com.marcelo.restaurante.model.Usuario;
import com.marcelo.restaurante.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findUserByEmail(email);
        if (usuario == null) {
            throw new UsernameNotFoundException("Email não encontrado: " + email);
        }
        //return new User(usuario.getEmail(), usuario.getSenha(), true, true, true, true, usuario.getAuthorities());
        return UsuarioDTO.build(usuario);
    }
}
