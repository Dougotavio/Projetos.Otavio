package com.artes.marciais.demo.model;

import java.util.Locale;

/**
 * Intensidade de contato que a pessoa prefere nos treinos. Orienta as
 * observacoes da recomendacao, sem alterar a pontuacao das artes.
 */
public enum Contato {

	LEVE("Prefiro contato leve"),
	MODERADO("Aceito contato moderado"),
	INTENSO("Aceito treinos de contato intenso");

	private final String rotulo;

	Contato(String rotulo) {
		this.rotulo = rotulo;
	}

	public String getRotulo() {
		return rotulo;
	}

	/** Resolve o valor recebido, assumindo {@link #MODERADO}. */
	public static Contato de(String valor) {
		if (valor == null) {
			return MODERADO;
		}
		String normalizado = valor.trim().toUpperCase(Locale.ROOT);
		for (Contato contato : values()) {
			if (contato.name().equals(normalizado)) {
				return contato;
			}
		}
		return MODERADO;
	}
}