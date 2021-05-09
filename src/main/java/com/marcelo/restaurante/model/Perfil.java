package com.marcelo.restaurante.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.marcelo.restaurante.enums.PerfilNome;

@Entity(name = "perfil")
public class Perfil {
	 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private PerfilNome nome;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PerfilNome getNome() {
		return nome;
	}

	public void setNome(PerfilNome nome) {
		this.nome = nome;
	}


}
