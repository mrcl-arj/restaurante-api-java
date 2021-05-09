package com.marcelo.restaurante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.restaurante.DTO.ClienteDTO;
import com.marcelo.restaurante.service.ClienteService;
import com.marcelo.restaurante.util.ResultadoRequisicao;

@RestController
public class ClienteController {

	@Autowired
    private ClienteService clienteService;
	
    @PostMapping(value = "/cliente/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody ClienteDTO clienteDTO) {
		ResultadoRequisicao resultado = clienteService.registrar(clienteDTO);
		return new ResponseEntity<>(resultado.getMensagem(), resultado.getStatus());
    }

}
