package com.artes.marciais.demo.model;

/**
 * Categoria que agrupa as artes por tipo de dominante tecnica.
 */
public enum Disciplina {

	GRAPPLING("Grappling", "Predomina o controle por alavancas e a luta no chao."),
	STRIKING("Striking", "Predominam golpes de mao e perna a media distancia."),
	THROWING("Throwing", "Predominam projecoes: derrubar e controlar o oponente em pe."),
	MISTO("Misto", "Combina golpe, projecao e trabalho no chao.");

	private final String rotulo;
	private final String descricao;

	Disciplina(String rotulo, String descricao) {
		this.rotulo = rotulo;
		this.descricao = descricao;
	}

	public String getRotulo() {
		return rotulo;
	}

	public String getDescricao() {
		return descricao;
	}
}
