package com.marcelo.restaurante.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
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
    }
    
    @GetMapping(value = "/pedido/status/{id}")
    public ResponseEntity<Pedido> statusPedido(@PathVariable Long id) {
    	try {
    		Optional<Pedido> pedidoAux = pedidoRepository.findById(id);
    	    if(pedidoAux.isPresent()){
    	    	Pedido pedido = pedidoAux.get();
    	        return new ResponseEntity<Pedido>(pedido, HttpStatus.OK);
    	     } else {
    	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	     }
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
    }
    
    @PostMapping(value = "/pedido/finalizados")
    public ResponseEntity<String> consultarFinalizados() {
    	try {
    		List<Pedido> pedidos = pedidoRepository.pedidosFinalizados();
    		pedidos.forEach(pedido->{
    			System.out.println(pedido);
    		});
//    	    if(pedidoAux.isPresent()){
//    	    	Pedido pedido = pedidoAux.get();
    	        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
//    	     } else {
//    	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    	     }
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
    }
    
    @PostMapping(value = "/pedido/atualizarStatus/")
    public ResponseEntity<Pedido> atualizarStatus(@RequestBody JsonNode json) {
    	try {
    		long pedido_id = json.get("pedido_id").asLong();
    		String status = json.get("status").toString();
    		Optional<Pedido> pedidoAux = pedidoRepository.findById(pedido_id);
    	    if(pedidoAux.isPresent()){
    	    	Pedido pedido = pedidoAux.get();
    	    	pedido.setStatus(StatusPedido.valueOf(status));
    	    	pedidoRepository.save(pedido);
    	        return new ResponseEntity<Pedido>(pedido, HttpStatus.OK);
    	     } else {
    	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	     }
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
    }

}
