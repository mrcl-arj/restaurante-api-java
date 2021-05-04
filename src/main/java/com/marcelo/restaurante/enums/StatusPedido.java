package com.marcelo.restaurante.enums;

public enum StatusPedido {

	EM_PRODUCAO("Em produção"), CANCELADO("Cancelado"), AGUARDANDO_CONFIRMACAO("Aguandando confirmaçao"), FINALIZADO("Finalizado");

	private String value;

	private StatusPedido(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
