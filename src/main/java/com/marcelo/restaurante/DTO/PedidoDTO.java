package com.marcelo.restaurante.DTO;

import java.math.BigDecimal;
import java.util.List;

import com.marcelo.restaurante.model.Item;

public class PedidoDTO {
	private List<Item> itens;
    
    private BigDecimal valor;
    
    private String status;
    
    private Long tempoEspera;

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getTempoEspera() {
		return tempoEspera;
	}

	public void setTempoEspera(Long tempoEspera) {
		this.tempoEspera = tempoEspera;
	}

}
