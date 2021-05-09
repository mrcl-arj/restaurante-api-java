package com.marcelo.restaurante.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.marcelo.restaurante.DTO.ItemDTO;
import com.marcelo.restaurante.service.PedidoService;
import com.marcelo.restaurante.util.ResultadoRequisicao;

@RestController
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
    @PostMapping(value = "/pedido/solicitar")
    public ResponseEntity<?> solicitar(@RequestBody List<ItemDTO> itensDTO) {   	
		ResultadoRequisicao resultado = pedidoService.solicitar(itensDTO);
		return new ResponseEntity<>(resultado.getMensagem(), resultado.getStatus());
    }
    
    @GetMapping(value = "/pedido/status/{id}")
    public ResponseEntity<?> statusPedido(@PathVariable Long id) {
		ResultadoRequisicao resultado = pedidoService.statusPedido(id);
		return new ResponseEntity<>(resultado.getMensagem(), resultado.getStatus());
    }
    
    @PostMapping(value = "/pedido/finalizados")
    public ResponseEntity<?> consultarFinalizados() {
		ResultadoRequisicao resultado = pedidoService.pedidosFinalizados();
		return new ResponseEntity<>(resultado.getMensagem(), resultado.getStatus());
    }
    
    @PutMapping(value = "/pedido/atualizarStatus/{id}")
    public ResponseEntity<?> atualizarStatus(@PathVariable Long id, @RequestBody JsonNode json) {
    	String status = json.get("status").toString();
		ResultadoRequisicao resultado = pedidoService.atualizarStatus(id, status);
		return new ResponseEntity<>(resultado.getMensagem(), resultado.getStatus());
    }

}
