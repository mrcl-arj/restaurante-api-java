package com.marcelo.restaurante.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.marcelo.restaurante.DTO.ItemDTO;
import com.marcelo.restaurante.enums.StatusPedido;
import com.marcelo.restaurante.model.Item;
import com.marcelo.restaurante.model.Pedido;
import com.marcelo.restaurante.model.Produto;
import com.marcelo.restaurante.repository.ItemRepository;
import com.marcelo.restaurante.repository.PedidoRepository;
import com.marcelo.restaurante.repository.ProdutoRepository;
import com.marcelo.restaurante.repository.UsuarioRepository;
import com.marcelo.restaurante.util.ResultadoRequisicao;
import com.marcelo.restaurante.util.Util;

@Service
public class PedidoService {
	
	@Autowired
    private PedidoRepository pedidoRepository;
	
	@Autowired
    private ProdutoRepository produtoRepository;
	
	@Autowired
    private ItemRepository itemRepository;
	

	
	public ResultadoRequisicao solicitar(List<ItemDTO> itensDTO) {
		ResultadoRequisicao resultado = new ResultadoRequisicao();
		
    	BigDecimal valorTotal = new BigDecimal(0);
    	Pedido pedido = new Pedido();
    	List<Item> itens = new ArrayList<>();
    	
    	for(ItemDTO itemDTO : itensDTO) {
        	Optional<Produto> produtoAux = produtoRepository.findById(itemDTO.getProdutoId());
        	if(!produtoAux.isPresent()) {
        		resultado.setStatus(HttpStatus.NOT_ACCEPTABLE);
        		resultado.setMensagem("Produto não encontrado!");
        		return resultado;
        	}else {
        		Produto produto = produtoAux.get();
        		Item item = new Item();
        		item.setProduto(produto);
        		item.setQuantidade(itemDTO.getQuantidade());
        		itens.add(item);
        		valorTotal = valorTotal.add(produto.getPreco());
        	}
    	}
    	
    	pedido.setUsuario(Util.buscaUsuarioLogado());
    	pedido.setItens(itens);
    	pedido.setValor(valorTotal);
    	pedido.setStatus(StatusPedido.AGUARDANDO_CONFIRMACAO);
    	
    	Pedido pedidoAux = pedidoRepository.save(pedido);
    	pedidoAux.getItens().forEach(i->{
    		i.setPedido(pedidoAux);
    		itemRepository.save(i);
    	});
		
		resultado.setStatus(HttpStatus.OK);
		resultado.setMensagem("Pedido registrado com sucesso");
		return resultado;	
	}
	
	public ResultadoRequisicao statusPedido(Long id) {
		ResultadoRequisicao resultado = new ResultadoRequisicao();
		
    	try {
    		Optional<Pedido> pedidoAux = pedidoRepository.findById(id);
    	    if(pedidoAux.isPresent()){
    	    	Pedido pedido = pedidoAux.get();
    			resultado.setStatus(HttpStatus.OK);
    			resultado.setMensagem(pedido.getStatus().toString());
    			return resultado;	
    	     } else {
         		resultado.setStatus(HttpStatus.NOT_FOUND);
         		resultado.setMensagem("Produto não encontrado!");
         		return resultado;
    	     }
		} catch (Exception e) {
    		resultado.setStatus(HttpStatus.BAD_REQUEST);
    		resultado.setMensagem("Erro ao cadastrar produto!");
			return resultado;
		}
		
	}
	
	public ResultadoRequisicao pedidosFinalizados() {
		ResultadoRequisicao resultado = new ResultadoRequisicao();
    	ObjectMapper mapper = new ObjectMapper();
    	ArrayNode noArrayPedido = mapper.createArrayNode();
    	List<ObjectNode> noPedido = new ArrayList<>();

    	String jsonString = null;
    	
    	try {
    		List<Pedido> pedidos = pedidoRepository.pedidosFinalizados();
    		if(!pedidos.isEmpty()) {
        		pedidos.forEach(pedido->{
        	    	ObjectNode childNode1 = mapper.createObjectNode();
        	    	childNode1.put("numeroPedido", pedido.getId());
        	    	childNode1.put("valor", Util.formatarBigDecimalParaMoeda(pedido.getValor()));
        	    	
        	    	ArrayNode noArrayItem = mapper.createArrayNode();
        	    	List<ObjectNode> noItem = new ArrayList<>();
        	    	
        	    	pedido.getItens().forEach(i -> {
            	    	ObjectNode childNode2 = mapper.createObjectNode();
            	    	childNode2.put("produto", i.getProduto().getNome());
            	    	childNode2.put("precoIndividual", Util.formatarBigDecimalParaMoeda(i.getProduto().getPreco()));
            	    	childNode2.put("quantidade", i.getQuantidade());
            	    	noItem.add(childNode2);
        	    	});
        	    	
        	    	noArrayItem.addAll(noItem);
        	    	childNode1.set("itens", noArrayItem);
        	    	noPedido.add(childNode1);
        		});
    	    	noArrayPedido.addAll(noPedido);
        		jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(noArrayPedido);
    			resultado.setStatus(HttpStatus.OK);
    			resultado.setMensagem(jsonString);
    		}else {
    			resultado.setStatus(HttpStatus.NO_CONTENT);
    			resultado.setMensagem("Nenhum pedido encontrado");
    		}
    		
    		return resultado;	
		} catch (Exception e) {
    		resultado.setStatus(HttpStatus.BAD_REQUEST);
    		resultado.setMensagem("Erro!");
			return resultado;
		}	
	}
	
	public ResultadoRequisicao atualizarStatus(Long id, String status) {
		ResultadoRequisicao resultado = new ResultadoRequisicao();
		
    	try {
    		Optional<Pedido> pedidoAux = pedidoRepository.findById(id);
    	    if(pedidoAux.isPresent()){
    	    	try {
        	    	Pedido pedido = pedidoAux.get();
        	    	pedido.setStatus(StatusPedido.valueOf(status.replaceAll("\"", "")));
    	    		Pedido p = pedidoRepository.save(pedido);
    	    		resultado.setStatus(HttpStatus.OK);
        			resultado.setMensagem(p.getStatus().toString());
        			return resultado;	
    			} catch (Exception e) {
    				e.printStackTrace();
    	    		resultado.setStatus(HttpStatus.BAD_REQUEST);
    	    		resultado.setMensagem("Erro ao atualizar status do pedido!");
    				return resultado;
    			}
    	     } else {
         		resultado.setStatus(HttpStatus.NOT_FOUND);
         		resultado.setMensagem("Produto não encontrado!");
         		return resultado;
    	     }
		} catch (Exception e) {
    		resultado.setStatus(HttpStatus.BAD_REQUEST);
    		resultado.setMensagem("Erro ao realizar açao!");
			return resultado;
		}
	}

}
