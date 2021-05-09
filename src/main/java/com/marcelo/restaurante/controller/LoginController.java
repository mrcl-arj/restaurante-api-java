package com.marcelo.restaurante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.restaurante.DTO.LoginDTO;
import com.marcelo.restaurante.DTO.AutenticacaoDTO;
import com.marcelo.restaurante.repository.UsuarioRepository;
import com.marcelo.restaurante.service.UsuarioDetailsServiceImpl;
import com.marcelo.restaurante.util.JwtUtil;

@RestController
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder senhaEncoder;

    @Autowired
    UsuarioDetailsServiceImpl usuarioDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/checkUser")
    public String checkUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return  currentPrincipalName;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginDTO loginDTO) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getSenha())
        );

        UserDetails userDetails = usuarioDetailsService.loadUserByUsername(loginDTO.getEmail());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AutenticacaoDTO("Bearer "+jwt));

    }
}
