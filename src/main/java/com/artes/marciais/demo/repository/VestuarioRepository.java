package com.artes.marciais.demo.repository;

import java.util.List;
import java.util.Optional;

import com.artes.marciais.demo.model.Vestuario;

/**
 * Fonte de dados dos trajes das artes marciais do site.
 *
 * Mesma ideia de {@link MartialArtRepository}: a interface isola a origem dos
 * dados e permite trocar a implementacao sem tocar no controller.
 */
public interface VestuarioRepository {

	List<Vestuario> findAll();

	Optional<Vestuario> findBySlugArte(String slugArte);

	/** Implementacao em memoria, carregada por dados de exemplo. */
	class InMemory implements VestuarioRepository {

		private final List<Vestuario> vestuarios = VestuarioCatalogo.vestuarios();

		@Override
		public List<Vestuario> findAll() {
			return vestuarios;
		}

		@Override
		public Optional<Vestuario> findBySlugArte(String slugArte) {
			return vestuarios.stream()
					.filter(vestuario -> vestuario.slugArte().equals(slugArte))
					.findFirst();
		}
	}
}