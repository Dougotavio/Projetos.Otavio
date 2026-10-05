package com.artes.marciais.demo.model;

import java.util.Map;

/**
 * Criterio tecnico informado no questionario de recomendacao.
 *
 * Cada constante carrega o rotulo exibido no formulario e os pontos que
 * adiciona a cada arte. Centralizar as duas coisas aqui evita que a lista de
 * opcoes do controller e as regras de pontuacao do servicedivirjam.
 */
public enum Preferencia {

	CHUTES("Gosto de chutes e movimentacao", 5, "taekwondo"),
	GOLPES_VARIADOS("Quero combinar punhos, chutes, joelhos e cotovelos", 5, "muay-thai"),
	ATAQUE_AGRESSIVO("Prefiro tecnicas mais agressivas e diretas", 5, "muay-thai"),
	DEFESA_CONTROLE("Prefiro evitar confronto, priorizando controle e imobilizacoes", 5, "jiu-jitsu"),
	PROJECOES("Tenho interesse em projecoes e quedas", 5, "judo"),
	LUTA_NO_SOLO("Prefiro controle e tecnicas no chao", 5, "jiu-jitsu"),
	SEM_PREFERENCIA("Ainda nao tenho preferencia", 0, null);

	private final String rotulo;
	private final int pontos;
	private final String arteFavorita;

	Preferencia(String rotulo, int pontos, String arteFavorita) {
		this.rotulo = rotulo;
		this.pontos = pontos;
		this.arteFavorita = arteFavorita;
	}

	public String getRotulo() {
		return rotulo;
	}

	/** Pontos somados a arte predileta; zero quando nao ha preferencia definida. */
	public int getPontos() {
		return pontos;
	}

	/** Slug da arte que mais se aproxima desta preferencia, ou {@code null}. */
	public String getArteFavorita() {
		return arteFavorita;
	}

	/**
	 * Resolve o texto recebido no formulario, ignorando maiusculas e espacos.
	 * Devolve {@link #SEM_PREFERENCIA} quando o valor nao e reconhecido, em vez
	 * de lancar excecao.
	 */
	public static Preferencia de(String valor) {
		if (valor == null) {
			return SEM_PREFERENCIA;
		}
		String normalizado = valor.trim().toUpperCase(java.util.Locale.ROOT);
		for (Preferencia preferencia : values()) {
			if (preferencia.name().equals(normalizado)) {
				return preferencia;
			}
		}
		return SEM_PREFERENCIA;
	}
}