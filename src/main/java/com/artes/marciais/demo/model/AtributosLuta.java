package com.artes.marciais.demo.model;

/**
 * Atributos tecnicos de uma arte marcial, usados pela simulacao de duelo.
 *
 * Todos os campos vao de 0 a 5 e representam o quanto a arte é forte
 * naquele aspecto, nao o quanto o lutador domina.
 *
 * @param slug        identificador da arte no catalogo
 * @param golpes      potencia dos golpes de mao e perna
 * @param defesa      capacidade de evitar ou amenizar golpes
 * @param projecao    eficiencia em derrubar e controlar em pe
 * @param clinch      dominate da luta de corpo a corpo
 * @param chao        dominio do trabalho no solo
 * @param controle    dominio de posicoes e submissao
 * @param resistencia capacidade de manter o ritmo ao longo do combate
 * @param agressividade tendencia a pressionar o oponente
 * @param alcance     distancia preferida de trabalho, em metros
 */
public record AtributosLuta(
		String slug,
		int golpes,
		int defesa,
		int projecao,
		int clinch,
		int chao,
		int controle,
		int resistencia,
		int agressividade,
		int alcance) {

	/**
	 * Soma simples dos atributos, usada como base do confronto.
	 * Mantida simples de proposito: e uma leitura didatica, nao uma formula
	 * cientifica de combate.
	 */
	public int total() {
		return golpes + defesa + projecao + clinch + chao + controle + resistencia;
	}

	/**
	 * Frase curta explicando o perfil tatico da arte, montada a partir dos
	 * dois atributos mais altos. Ajuda o usuario a entender o resultado do duelo.
	 */
	public String resumoPerfil() {
		String principal;
		if (projetor() >= golpes && projetor() >= controle) {
			principal = "privilegia derrubar e controlar o oponente em pe";
		} else if (controle >= chao && controle >= golpes) {
			principal = "privilegia controlar posicoes e levar o combate ao chao";
		} else {
			principal = "privilegia golpes diretos a media distancia";
		}
		return principal + " (alcance ideal de " + alcance + " m)";
	}

	private int projetor() {
		return Math.max(projecao, Math.max(chao, clinch));
	}
}