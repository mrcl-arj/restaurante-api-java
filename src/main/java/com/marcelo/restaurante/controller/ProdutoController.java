package com.marcelo.restaurante.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.restaurante.DTO.ProdutoDTO;
import com.marcelo.restaurante.service.ProdutoService;
import com.marcelo.restaurante.util.ResultadoRequisicao;

@RestController
public class ProdutoController {
	
	@Autowired
    private ProdutoService produtoService;
	
    @PostMapping(value = "/produto/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody ProdutoDTO produtoDTO) {
    	ResultadoRequisicao resultado = produtoService.cadastrar(produtoDTO);
    	return new ResponseEntity<>(resultado.getMensagem(), resultado.getStatus());
    }
    
    @PutMapping(value = "/produto/editar/{id}")
    @PreAuthorize("hasRole('GESTOR')")
    public ResponseEntity<String> editar(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO) {
    	ResultadoRequisicao resultado = produtoService.editar(id, produtoDTO);
    	return new ResponseEntity<>(resultado.getMensagem(), resultado.getStatus());
    }

}
