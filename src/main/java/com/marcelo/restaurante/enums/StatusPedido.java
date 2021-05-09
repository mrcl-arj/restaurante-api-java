package com.marcelo.restaurante.enums;

public enum StatusPedido {

	AGUARDANDO_CONFIRMACAO("Aguandando confirmaçao"), EM_PRODUCAO("Em produção"), FINALIZADO("Finalizado"), CANCELADO("Cancelado");

	private String value;

	private StatusPedido(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
