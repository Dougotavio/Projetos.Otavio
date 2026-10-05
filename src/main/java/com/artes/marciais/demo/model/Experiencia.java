package com.artes.marciais.demo.model;

import java.util.Locale;

/**
 * Nivel de experiencia declarado no questionario. Orienta as observacoes da
 * recomendacao, sem alterar a pontuacao das artes.
 */
public enum Experiencia {

	INICIANTE("Estou comecando agora"),
	INTERMEDIARIO("Ja pratiquei por algum tempo"),
	AVANCADO("Tenho experiencia avancada");

	private final String rotulo;

	Experiencia(String rotulo) {
		this.rotulo = rotulo;
	}

	public String getRotulo() {
		return rotulo;
	}

	/** Resolve o valor recebido, assumindo {@link #INICIANTE}. */
	public static Experiencia de(String valor) {
		if (valor == null) {
			return INICIANTE;
		}
		String normalizado = valor.trim().toUpperCase(Locale.ROOT);
		for (Experiencia experiencia : values()) {
			if (experiencia.name().equals(normalizado)) {
				return experiencia;
			}
		}
		return INICIANTE;
	}
}