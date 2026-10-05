package com.artes.marciais.demo.repository;

import java.util.List;
import java.util.Optional;

import com.artes.marciais.demo.model.AtributosLuta;

/**
 * Fonte de dados dos atributos de combate de cada arte.
 *
 * Mesma ideia de {@link MartialArtRepository}: a interface isola a origem e
 * permite trocar a implementacao sem tocar no controller.
 */
public interface AtributosLutaRepository {

	List<AtributosLuta> findAll();

	Optional<AtributosLuta> findBySlug(String slug);

	/** Implementacao em memoria, com os atributos fixos da simulacao. */
	class InMemory implements AtributosLutaRepository {

		private final List<AtributosLuta> atributos = AtributosCatalogo.atributos();

		@Override
		public List<AtributosLuta> findAll() {
			return atributos;
		}

		@Override
		public Optional<AtributosLuta> findBySlug(String slug) {
			return atributos.stream().filter(a -> a.slug().equals(slug)).findFirst();
		}
	}
}