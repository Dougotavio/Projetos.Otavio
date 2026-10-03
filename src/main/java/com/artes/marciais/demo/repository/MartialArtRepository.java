package com.artes.marciais.demo.repository;

import java.util.List;
import java.util.Optional;

import com.artes.marciais.demo.model.MartialArt;

/**
 * Fonte de dados das artes marciais site's.
 *
 * Em um projeto real isto daria lugar a um repositorio com banco de dados;
 * a interface permanece igual para nao acoplar o controller a origem dos dados.
 */
public interface MartialArtRepository {

	List<MartialArt> findAll();

	Optional<MartialArt> findBySlug(String slug);

	/** Implementacao em memoria, carregada por dados de exemplo. */
	class InMemory implements MartialArtRepository {

		private final List<MartialArt> artes = ArtesCatalogo.artes();

		@Override
		public List<MartialArt> findAll() {
			return artes;
		}

		@Override
		public Optional<MartialArt> findBySlug(String slug) {
			return artes.stream().filter(arte -> arte.slug().equals(slug)).findFirst();
		}
	}
}
