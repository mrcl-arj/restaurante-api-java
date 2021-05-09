package com.marcelo.restaurante.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.marcelo.restaurante.enums.CategoriaProduto;


@Entity(name = "produto")
public class Produto {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
    
    @Column(nullable = false)
    private BigDecimal preco;
    
    private Long quantidadeEstoque;
    
    private Long minDeProducao;
	
    @Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private CategoriaProduto categoria;

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

	public CategoriaProduto getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaProduto categoria) {
		this.categoria = categoria;
	}

}
