package com.marcelo.restaurante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.restaurante.model.Gestor;
import com.marcelo.restaurante.model.Usuario2;
import com.marcelo.restaurante.repository.GestorRepository;
import com.marcelo.restaurante.repository.Usuario2Repository;

@RestController
public class GestorController {
	
	@Autowired
    private GestorRepository gestorRepository;
	
	@Autowired
    private Usuario2Repository usuarioRepository;
	
    @PostMapping(value = "/gestor/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody Gestor gestor) {
    	try {
    		Usuario2 usuario = usuarioRepository.save(gestor.getUsuario());
    		gestor.setUsuario(usuario);
    		gestorRepository.save(gestor);
    		
	    	return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    	} catch (Exception e) {
    		return new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
    	}
    }
    

}
