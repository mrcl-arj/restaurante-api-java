package com.marcelo.restaurante.enums;

public enum CategoriaProduto {
	BEBIDA("Bebida"), COMIDA("Comida"), SOBREMESA("Sobremesa");

	private String value;

	private CategoriaProduto(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
