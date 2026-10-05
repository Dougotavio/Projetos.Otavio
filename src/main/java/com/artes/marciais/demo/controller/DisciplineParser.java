package com.artes.marciais.demo.controller;

import java.util.Locale;
import java.util.Optional;

import com.artes.marciais.demo.model.Disciplina;

/**
 * Converte o valor textual da URL em {@link Disciplina}.
 *
 * A converso e feita aqui, e nao pelo Spring, para que um valor desconhecido
 * vire uma pagina 404 do site em vez de um erro 500.
 */
final class DisciplineParser {

	private DisciplineParser() {
	}

	static Optional<Disciplina> buscar(String valor) {
		if (valor == null || valor.isBlank()) {
			return Optional.empty();
		}
		String normalizado = valor.trim().toUpperCase(Locale.ROOT);
		for (Disciplina disciplina : Disciplina.values()) {
			if (disciplina.name().equals(normalizado)) {
				return Optional.of(disciplina);
			}
		}
		return Optional.empty();
	}
}