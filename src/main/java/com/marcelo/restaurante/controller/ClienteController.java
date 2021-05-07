package com.marcelo.restaurante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.restaurante.model.Cliente;
import com.marcelo.restaurante.model.Usuario2;
import com.marcelo.restaurante.repository.ClienteRepository;
import com.marcelo.restaurante.repository.Usuario2Repository;

@RestController
public class ClienteController {

	@Autowired
    private ClienteRepository clienteRepository;
	
	@Autowired
    private Usuario2Repository usuarioRepository;
	
    @PostMapping(value = "/cliente/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody Cliente cliente) {
    	try {
    		Usuario2 usuario = usuarioRepository.save(cliente.getUsuario());
    		cliente.setUsuario(usuario);
    		clienteRepository.save(cliente);
    		
	    	return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    	} catch (Exception e) {
    		return new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
    	}
    }

}
