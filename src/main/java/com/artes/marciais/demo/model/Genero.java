package com.artes.marciais.demo.model;

import java.util.Locale;

/**
 * Genero informado no questionario. Registrado no perfil por transparencia,
 * sem interferir na pontuacao da recomendacao.
 */
public enum Genero {

	PREFIRO_NAO_INFORMAR("Prefiro nao informar"),
	MULHER("Mulher"),
	HOMEM("Homem"),
	NAO_BINARIO("Nao binario");

	private final String rotulo;

	Genero(String rotulo) {
		this.rotulo = rotulo;
	}

	public String getRotulo() {
		return rotulo;
	}

	/** Resolve o valor recebido, assumindo {@link #PREFIRO_NAO_INFORMAR}. */
	public static Genero de(String valor) {
		if (valor == null) {
			return PREFIRO_NAO_INFORMAR;
		}
		String normalizado = valor.trim().toUpperCase(Locale.ROOT);
		for (Genero genero : values()) {
			if (genero.name().equals(normalizado)) {
				return genero;
			}
		}
		return PREFIRO_NAO_INFORMAR;
	}
}