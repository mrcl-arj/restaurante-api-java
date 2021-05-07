package com.marcelo.restaurante.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "cliente")
public class Cliente {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String nome;
	
	private String telefone;
	
	private LocalDate nascimento;
	
    @ManyToOne
    @JoinColumn(name = "usuario_email")
    private Usuario2 usuario;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}

	public Usuario2 getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario2 usuario) {
		this.usuario = usuario;
	}

}
