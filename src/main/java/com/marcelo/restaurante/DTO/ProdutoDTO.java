package com.marcelo.restaurante.DTO;

import java.math.BigDecimal;

public class ProdutoDTO {
	
    private String nome;
    
    private BigDecimal preco;
    
    private Long quantidadeEstoque;
    
    private Long minDeProducao;
	
	private String categoria;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Long getQuantidadeEstoque() {
		return quantidadeEstoque;
	}

	public void setQuantidadeEstoque(Long quantidadeEstoque) {
		this.quantidadeEstoque = quantidadeEstoque;
	}

	public Long getMinDeProducao() {
		return minDeProducao;
	}

	public void setMinDeProducao(Long minDeProducao) {
		this.minDeProducao = minDeProducao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}
