package com.marcelo.restaurante.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.marcelo.restaurante.model.Produto;
import com.marcelo.restaurante.repository.ProdutoRepository;

@RestController
public class ProdutoController {
	
	@Autowired
    private ProdutoRepository produtoRepository;
	
    @PostMapping(value = "/produto/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody Produto produto) {
    	try {
    		produtoRepository.save(produto);
    		
	    	return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    	} catch (Exception e) {
    		return new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
    	}
    }
    
    @PutMapping(value = "/produto/editar/{id}")
    public ResponseEntity<String> editar(@PathVariable Long id, @RequestBody Produto produto) {
    	try {
    		
    		Optional<Produto> produtoAux = produtoRepository.findById(id);
    	    if(produtoAux.isPresent()){
    	    	Produto produtoAntigo = produtoAux.get();
    	    	produtoAntigo.setNome(produto.getNome());
    	    	produtoAntigo.setCategoria(produto.getCategoria());
    	    	produtoAntigo.setMinDeProducao(produto.getMinDeProducao());
    	    	produtoAntigo.setPreco(produto.getPreco());
    	    	produtoAntigo.setQuantidadeEstoque(produto.getQuantidadeEstoque());
    	    	
    	    	produtoRepository.save(produtoAntigo);
    	        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    	     } else {
    	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	     }
    		
    	} catch (Exception e) {
    		return new ResponseEntity<>("ERROR", HttpStatus.BAD_REQUEST);
    	}
    }

}
