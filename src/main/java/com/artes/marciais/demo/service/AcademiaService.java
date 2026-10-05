package com.artes.marciais.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.artes.marciais.demo.model.Academia;
import com.artes.marciais.demo.repository.AcademiaRepository;

/**
 * Regras de exibicao do mapa de academias: filtro por arte e por cidade.
 */
@Service
public class AcademiaService {

	private final AcademiaRepository repository;
	private final MartialArtService martialArtService;

	public AcademiaService(AcademiaRepository repository, MartialArtService martialArtService) {
		this.repository = repository;
		this.martialArtService = martialArtService;
	}

	public List<Academia> listarTodas() {
		return repository.findAll();
	}

	/**
	 * Filtra pelas artesInformadas. Sem filtro, devolve todas.
	 * Slugs desconhecidos sao ignorados em vez de quebrar a pagina.
	 */
	public List<Academia> listarPorArtes(List<String> slugs) {
		List<Academia> todas = repository.findAll();
		if (slugs == null || slugs.isEmpty()) {
			return todas;
		}
		Set<String> desejadas = Set.copyOf(slugs);
		return todas.stream()
				.filter(academia -> algumaArteCombina(academia, desejadas))
				.toList();
	}

	/** True quando a academia ensina ao menos uma das artes filtradas. */
	private boolean algumaArteCombina(Academia academia, Set<String> desejadas) {
		return academia.artes().stream().anyMatch(desejadas::contains);
	}

	/** Cidades distintas, para o seletor de regiao. */
	public List<String> cidades() {
		return repository.findAll().stream()
				.map(Academia::bairro)
				.map(bairro -> bairro.contains(",") ? bairro.split(", ")[1] : bairro)
				.distinct()
				.sorted()
				.toList();
	}

	public List<Academia> listarPorCidade(String cidade) {
		if (cidade == null || cidade.isBlank() || "todas".equalsIgnoreCase(cidade)) {
			return repository.findAll();
		}
		return repository.findAll().stream()
				.filter(academia -> cidadeDe(academia).equalsIgnoreCase(cidade))
				.toList();
	}

	private String cidadeDe(Academia academia) {
		String bairro = academia.bairro();
		return bairro.contains(",") ? bairro.split(", ")[1] : bairro;
	}

	public Optional<Academia> buscarPorSlug(String slug) {
		return repository.findBySlug(slug);
	}

	/** Slugs das artes que ao menos uma academia do catalogo ensina. */
	public List<String> artesComAcademia() {
		return repository.findAll().stream()
				.flatMap(academia -> academia.artes().stream())
				.distinct()
				.filter(slug -> martialArtService.buscarPorSlug(slug).isPresent())
				.toList();
	}
}