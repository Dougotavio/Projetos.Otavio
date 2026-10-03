package com.artes.marciais.demo.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.artes.marciais.demo.model.Disciplina;
import com.artes.marciais.demo.model.MartialArt;
import com.artes.marciais.demo.repository.MartialArtRepository;

/**
 * Regras de apresentacao do conteudo: ordenacao, filtro por disciplina e
 * estatisticas usadas na pagina inicial.
 */
@Service
public class MartialArtService {

	private final MartialArtRepository repository;

	public MartialArtService(MartialArtRepository repository) {
		this.repository = repository;
	}

	public List<MartialArt> listarTodas() {
		return repository.findAll();
	}

	public List<MartialArt> listarPorOrdemCronologica() {
		return repository.findAll().stream()
				.sorted(Comparator.comparingInt(MartialArt::anoOrigem))
				.toList();
	}

	public List<MartialArt> listarPorDisciplina(Disciplina disciplina) {
		return repository.findAll().stream()
				.filter(arte -> arte.disciplina() == disciplina)
				.toList();
	}

	public Optional<MartialArt> buscarPorSlug(String slug) {
		return repository.findBySlug(slug);
	}

	public List<Disciplina> disciplinas() {
		return List.of(Disciplina.values());
	}

	public long maisAntiga() {
		return repository.findAll().stream()
				.mapToLong(MartialArt::anoOrigem)
				.min()
				.orElse(0);
	}

	public long maisRecente() {
		return repository.findAll().stream()
				.mapToLong(MartialArt::anoOrigem)
				.max()
				.orElse(0);
	}
}
