package com.marcelo.restaurante.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestauranteController {
	
    @GetMapping(value = "/")
    public ResponseEntity<String> index() {
    	return new ResponseEntity<>("API RESTAURANTE", HttpStatus.OK);
    }
    
    @GetMapping(value = "/cardapio")
    public ResponseEntity<String> cardapio() {
    	return new ResponseEntity<>("API cardapio", HttpStatus.OK);
    }

}
