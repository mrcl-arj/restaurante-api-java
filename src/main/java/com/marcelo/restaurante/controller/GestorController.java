package com.marcelo.restaurante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.restaurante.DTO.GestorDTO;
import com.marcelo.restaurante.service.GestorService;
import com.marcelo.restaurante.util.ResultadoRequisicao;

@RestController
public class GestorController {
	
	@Autowired
    private GestorService gestorService;
	
	@PostMapping("/gestor/cadastrar")
	public ResponseEntity<String> createSurvivor(@RequestBody GestorDTO gestor) {
		ResultadoRequisicao resultado = gestorService.registrar(gestor);
		return new ResponseEntity<>(resultado.getMensagem(), resultado.getStatus());
	}
    

}
