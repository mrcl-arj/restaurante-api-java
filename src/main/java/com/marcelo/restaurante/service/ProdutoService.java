package com.marcelo.restaurante.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.marcelo.restaurante.DTO.ProdutoDTO;
import com.marcelo.restaurante.enums.CategoriaProduto;
import com.marcelo.restaurante.model.Produto;
import com.marcelo.restaurante.repository.ProdutoRepository;
import com.marcelo.restaurante.util.ResultadoRequisicao;

@Service
public class ProdutoService {
	
	@Autowired
    private ProdutoRepository produtoRepository;
	
	public ResultadoRequisicao cadastrar(ProdutoDTO produtoDTO) {
		ResultadoRequisicao resultado = new ResultadoRequisicao();
		
    	try {
    		Produto produto = new Produto();
    		produto.setNome(produtoDTO.getNome());
    		produto.setPreco(produtoDTO.getPreco());
    		produto.setMinDeProducao(produtoDTO.getMinDeProducao());
    		produto.setQuantidadeEstoque(produtoDTO.getQuantidadeEstoque());
    		produto.setCategoria(CategoriaProduto.valueOf(produtoDTO.getCategoria()));
    		
    		produtoRepository.save(produto);
    		
    		
    		resultado.setStatus(HttpStatus.OK);
    		resultado.setMensagem("Produto cadastrado com sucesso!");
			return resultado;
    	} catch (Exception e) {
    		resultado.setStatus(HttpStatus.BAD_REQUEST);
    		resultado.setMensagem("Erro ao cadastrar produto!");
			return resultado;
    	}
		
	}
	
	public ResultadoRequisicao editar(Long id, ProdutoDTO produtoDTO) {
		ResultadoRequisicao resultado = new ResultadoRequisicao();
		
    	try {
    		Optional<Produto> produtoAux = produtoRepository.findById(id);
    	    if(produtoAux.isPresent()){
    	    	Produto produtoAntigo = produtoAux.get();
    	    	produtoAntigo.setNome(produtoDTO.getNome());
    	    	produtoAntigo.setCategoria(CategoriaProduto.valueOf(produtoDTO.getCategoria()));
    	    	produtoAntigo.setMinDeProducao(produtoDTO.getMinDeProducao());
    	    	produtoAntigo.setPreco(produtoDTO.getPreco());
    	    	produtoAntigo.setQuantidadeEstoque(produtoDTO.getQuantidadeEstoque());
    	    	
    	    	produtoRepository.save(produtoAntigo);
        		resultado.setStatus(HttpStatus.OK);
        		resultado.setMensagem("Produto editado com sucesso!");
    			return resultado;
    	     } else {
    	    	resultado.setStatus(HttpStatus.NOT_FOUND);
    	    	resultado.setMensagem("Produto não encontrado!");
    			return resultado;
    	     }
    	} catch (Exception e) {
    		resultado.setStatus(HttpStatus.BAD_REQUEST);
    		resultado.setMensagem("Erro ao editar produto!");
			return resultado;
    	}
		
	}
}
