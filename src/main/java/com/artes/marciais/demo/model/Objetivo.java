package com.artes.marciais.demo.model;

import java.util.List;

/**
 * Motivo principal que leva a pessoa a procurar uma arte marcial, com os
 * pontos distributedos entre as artes que atendem bem a esse objetivo.
 */
public enum Objetivo {

	DEFESA_PESSOAL("Aprender defesa pessoal", 1, "judo", "karate", "jiu-jitsu", "muay-thai"),
	CONDICIONAMENTO("Melhorar condicionamento fisico", 2, "taekwondo", "muay-thai", "boxe"),
	DISCIPLINA("Desenvolver disciplina e foco", 2, "judo", "karate", "taekwondo"),
	COMPETICAO("Preparar-me para competir", 1,
			"judo", "karate", "taekwondo", "jiu-jitsu", "muay-thai", "boxe");

	private final String rotulo;
	private final int pontos;
	private final List<String> artes;

	Objetivo(String rotulo, int pontos, String... artes) {
		this.rotulo = rotulo;
		this.pontos = pontos;
		this.artes = List.of(artes);
	}

	public String getRotulo() {
		return rotulo;
	}

	public int getPontos() {
		return pontos;
	}

	/** Slugs das artes que somam pontos para este objetivo. */
	public List<String> getArtes() {
		return artes;
	}
}