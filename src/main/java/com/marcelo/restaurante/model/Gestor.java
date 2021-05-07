package com.marcelo.restaurante.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "gestor")
public class Gestor {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private String nomeEstabelecimento;
	
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario2 usuario;

	public Usuario2 getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario2 usuario) {
		this.usuario = usuario;
	}

	public String getNomeEstabelecimento() {
		return nomeEstabelecimento;
	}

	public void setNomeEstabelecimento(String nomeEstabelecimento) {
		this.nomeEstabelecimento = nomeEstabelecimento;
	}

}

