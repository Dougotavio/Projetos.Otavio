package com.artes.marciais.demo.repository;

import java.util.List;

import com.artes.marciais.demo.model.AtributosLuta;

/**
 * Atributos de combate de cada arte, de 0 a 5.
 *
 * Os valores seguem o que o catalogo de {@code ArtesCatalogo} ja descreve:
 * o judo e forte em projecao e clinch mas fraco em golpes de mao; o jiu-jitsu
 * domina o chao e o controle; o muay thai e o boxe vivem dos golpes; o
 * taekwondo busca distancia; o karate equilibra golpe e defesa.
 *
 * E uma leitura didatica para estudo, nao um medidor de performance real.
 */
final class AtributosCatalogo {

	private AtributosCatalogo() {
	}

	static List<AtributosLuta> atributos() {
		return List.of(
				// slug, golpes, defesa, projecao, clinch, chao, controle, resistencia, agressividade, alcance
				new AtributosLuta("judo", 2, 2, 5, 5, 4, 3, 4, 3, 1),
				new AtributosLuta("karate", 4, 4, 2, 2, 1, 1, 3, 2, 2),
				new AtributosLuta("taekwondo", 4, 3, 4, 2, 1, 1, 3, 3, 3),
				new AtributosLuta("jiu-jitsu", 2, 2, 2, 4, 5, 5, 4, 3, 1),
				new AtributosLuta("muay-thai", 5, 3, 1, 5, 1, 1, 5, 5, 2),
				new AtributosLuta("boxe", 5, 3, 0, 3, 1, 1, 5, 4, 3));
	}
}