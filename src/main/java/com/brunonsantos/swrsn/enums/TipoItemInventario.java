package com.brunonsantos.swrsn.enums;

import lombok.Getter;

/**
 * @author bruno
 *
 */
@Getter
public enum TipoItemInventario {
	COMIDA("Comida", 1), AGUA("Água", 2), MUNICAO("Munição", 3), ARMA("Arma", 4);

	private final String item;
	private final Integer valor;

	TipoItemInventario(String item, Integer valor) {
		this.item = item;
		this.valor = valor;
	}
}
