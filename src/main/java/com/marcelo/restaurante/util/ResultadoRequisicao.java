package com.marcelo.restaurante.util;

import org.springframework.http.HttpStatus;

public class ResultadoRequisicao {
	
	private String mensagem;
	private HttpStatus status;
	
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}
