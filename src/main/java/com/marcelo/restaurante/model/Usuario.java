package com.marcelo.restaurante.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "usuario")
public class Usuario {
	
    @Id
	private String email;

	private String senha;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
