package com.marcelo.restaurante.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marcelo.restaurante.enums.StatusPedido;
import com.marcelo.restaurante.model.Item;
import com.marcelo.restaurante.model.Pedido;
import com.marcelo.restaurante.model.Produto;
import com.marcelo.restaurante.repository.ItemRepository;
import com.marcelo.restaurante.repository.PedidoRepository;
import com.marcelo.restaurante.repository.ProdutoRepository;

@RestController
public class PedidoController {
	
	@Autowired
    private PedidoRepository pedidoRepository;
	
	@Autowired
    private ProdutoRepository produtoRepository;
	
	@Autowired
    private ItemRepository itemRepository;
	
    @PostMapping(value = "/pedido/solicitar")
    public ResponseEntity<Pedido> solicitar(@RequestBody JsonNode json) {
    	
    	BigDecimal valorTotal = new BigDecimal(0);
    	Pedido pedido = new Pedido();
    	List<Item> itens = new ArrayList<>();
    	
    	JsonNode produtos = json.path("produtos");
    	
    	produtos.forEach(p-> {
    		System.out.println(p.get("produto_id").asLong());
    		System.out.println(p.get("quantidade").asLong());
    	});

    	
    	for(JsonNode produtoJson : produtos) {
    		
        	long produto_id = produtoJson.get("produto_id").asLong();
        	Optional<Produto> produtoAux = produtoRepository.findById(produto_id);
        	if(!produtoAux.isPresent()) {
        		return new ResponseEntity<>(new Pedido(), HttpStatus.NOT_ACCEPTABLE);
        	}else {
        		Produto produto = produtoAux.get();
        		Item item = new Item();
        		item.setProduto(produto);
        		item.setQuantidade(produtoJson.get("quantidade").asLong());
        		//itens.add(itemRepository.save(item));
        		itens.add(item);
        		valorTotal = valorTotal.add(produto.getPreco());
        	}
    	}
    	
    	pedido.setItens(itens);
    	pedido.setValor(valorTotal);
    	pedido.setStatus(StatusPedido.AGUARDANDO_CONFIRMACAO);
    	
    	Pedido pedidoAux = pedidoRepository.save(pedido);
    	pedidoAux.getItens().forEach(i->{
    		i.setPedido(pedidoAux);
    		itemRepository.save(i);
    	});

    	return new ResponseEntity<>(pedidoAux, HttpStatus.OK);

//    	produtos.forEach(produtoJson->{
//        	long produto_id = produtoJson.get("produto_id").asLong();
//        	Optional<Produto> produtoAux = pedidoRepository.findById(produto_id);
//        	if(!produtoAux.isPresent()) {
//        		return new ResponseEntity<>("ERROR", HttpStatus.NO_CONTENT);
//        	}else {
//        		Produto produto = produtoAux.get();
//        		item.setProduto(produto);
//        		item.setQuantidade(json.get("quantidade").asLong());
//        		pedido.getItens().add(item) ;
//        		valorTotal = valorTotal.add(produto.getPreco());
//        	}
//        	pedido.setValor(valorTotal);
//        	pedido.setStatus(StatusPedido.AGUARDANDO_CONFIRMACAO);
//        	return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
//    	});
    }

}
