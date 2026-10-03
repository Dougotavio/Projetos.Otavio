package com.artes.marciais.demo.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.artes.marciais.demo.model.Vestuario;
import com.artes.marciais.demo.repository.VestuarioRepository;

/**
 * Regras de apresentacao da pagina de roupas: o catalogo de trajes e a
 * organizacao por arte, para o template percorrer as artes e exibir o traje
 * correspondente sem repetir o nome da arte em cada bloco.
 */
@Service
public class VestuarioService {

	private final VestuarioRepository repository;

	public VestuarioService(VestuarioRepository repository) {
		this.repository = repository;
	}

	public List<Vestuario> listarTodas() {
		return repository.findAll();
	}

	public Optional<Vestuario> buscarPorArte(String slugArte) {
		return repository.findBySlugArte(slugArte);
	}

	/**
	 * Mapa slug da arte para traje, na ordem do catalogo, usado pela pagina
	 * /roupas.
	 */
	public Map<String, Vestuario> porArte() {
		return repository.findAll().stream()
				.collect(Collectors.toMap(Vestuario::slugArte, Function.identity(),
						(anterior, novo) -> anterior, LinkedHashMap::new));
	}
}