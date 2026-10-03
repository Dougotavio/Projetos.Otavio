package com.artes.marciais.demo.model;

import java.util.List;

/**
 * Registro imutavel de uma arte marcial, com sua historia e analise
 * de pontos fortes e fracos.
 *
 * @param slug         identificador usado na URL
 * @param nome         nome oficial em portugues
 * @param nomeNativo   nome no idioma de origem
 * @param origem       pais ou regiao de origem
 * @param anoOrigem    ano aproximado de fundacao
 * @param disciplina   categoria tecnica dominante
 * @param resumo       frase curta usada nos cartoes
 * @param historia     texto com o contexto historico
 * @param pontosFortes lista de vantagens
 * @param pontosFracos lista de desvantagens
 * @param icone        simbolo visual usado no card
 * @param videoId      identificador do video de demonstracao no YouTube
 */
public record MartialArt(
		String slug,
		String nome,
		String nomeNativo,
		String origem,
		int anoOrigem,
		Disciplina disciplina,
		String resumo,
		String historia,
		List<Destaque> pontosFortes,
		List<Destaque> pontosFracos,
		String icone,
		String videoId) {

	public String anoFormatado() {
		return anoOrigem + " d.C.";
	}
}
