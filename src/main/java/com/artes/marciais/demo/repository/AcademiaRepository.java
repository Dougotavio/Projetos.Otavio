package com.artes.marciais.demo.repository;

import java.util.List;
import java.util.Optional;

import com.artes.marciais.demo.model.Academia;

/**
 * Fonte de dados das academias exibidas no mapa.
 *
 * Mesma ideia de {@link MartialArtRepository}: a interface isola a origem e
 * permite trocar a implementacao sem tocar no controller.
 */
public interface AcademiaRepository {

	List<Academia> findAll();

	Optional<Academia> findBySlug(String slug);

	/** Implementacao em memoria, carregada com o catalogo de exemplo. */
	class InMemory implements AcademiaRepository {

		private final List<Academia> academias = AcademiaCatalogo.academias();

		@Override
		public List<Academia> findAll() {
			return academias;
		}

		@Override
		public Optional<Academia> findBySlug(String slug) {
			return academias.stream().filter(a -> a.slug().equals(slug)).findFirst();
		}
	}
}