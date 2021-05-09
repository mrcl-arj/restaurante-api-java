package com.marcelo.restaurante.service;

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
        Usuario jwtUser = usuarioRepository.findUserByEmail(email);
        if (jwtUser == null) {
            throw new UsernameNotFoundException("Email não encontrado: " + email);
        }
        return new User(jwtUser.getEmail(), jwtUser.getSenha(), new ArrayList<>());
    }
}
