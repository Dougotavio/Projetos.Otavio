package com.artes.marciais.demo.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.artes.marciais.demo.model.MartialArt;
import com.artes.marciais.demo.model.PerfilRecomendacao;

@Service
public class RecomendacaoService {

	private static final Set<String> GENEROS = Set.of(
			"MULHER", "HOMEM", "NAO_BINARIO", "PREFIRO_NAO_INFORMAR");
	private static final Set<String> OBJETIVOS = Set.of(
			"DEFESA_PESSOAL", "CONDICIONAMENTO", "DISCIPLINA", "COMPETICAO");
	private static final Set<String> PREFERENCIAS = Set.of(
			"CHUTES", "GOLPES_VARIADOS", "ATAQUE_AGRESSIVO", "DEFESA_CONTROLE",
			"PROJECOES", "LUTA_NO_SOLO", "SEM_PREFERENCIA");
	private static final Set<String> EXPERIENCIAS = Set.of(
			"INICIANTE", "INTERMEDIARIO", "AVANCADO");
	private static final Set<String> CONTATOS = Set.of(
			"LEVE", "MODERADO", "INTENSO");

	private final MartialArtService martialArtService;

	public RecomendacaoService(MartialArtService martialArtService) {
		this.martialArtService = martialArtService;
	}

	public Map<String, String> validar(PerfilRecomendacao perfil) {
		Map<String, String> erros = new LinkedHashMap<>();

		if (perfil.getIdade() == null || perfil.getIdade() < 6 || perfil.getIdade() > 100) {
			erros.put("idade", "Informe uma idade entre 6 e 100 anos.");
		}
		if (perfil.getAlturaCm() == null || perfil.getAlturaCm() < 80 || perfil.getAlturaCm() > 230) {
			erros.put("alturaCm", "Informe uma altura entre 80 e 230 cm.");
		}
		if (perfil.getPesoKg() == null || !Double.isFinite(perfil.getPesoKg())
				|| perfil.getPesoKg() < 15 || perfil.getPesoKg() > 300) {
			erros.put("pesoKg", "Informe um peso entre 15 e 300 kg.");
		}
		validarOpcao("genero", perfil.getGenero(), GENEROS, erros);
		validarOpcao("objetivo", perfil.getObjetivo(), OBJETIVOS, erros);
		validarOpcao("preferencia", perfil.getPreferencia(), PREFERENCIAS, erros);
		validarOpcao("experiencia", perfil.getExperiencia(), EXPERIENCIAS, erros);
		validarOpcao("contato", perfil.getContato(), CONTATOS, erros);
		return erros;
	}

	public Resultado recomendar(PerfilRecomendacao perfil) {
		Map<String, Integer> pontos = new LinkedHashMap<>();
		for (MartialArt arte : martialArtService.listarTodas()) {
			pontos.put(arte.slug(), 0);
		}

		switch (perfil.getPreferencia()) {
		case "CHUTES" -> adicionar(pontos, "taekwondo", 5);
		case "GOLPES_VARIADOS" -> adicionar(pontos, "muay-thai", 5);
		case "ATAQUE_AGRESSIVO" -> adicionar(pontos, "muay-thai", 5);
		case "DEFESA_CONTROLE" -> adicionar(pontos, "jiu-jitsu", 5);
		case "PROJECOES" -> adicionar(pontos, "judo", 5);
		case "LUTA_NO_SOLO" -> adicionar(pontos, "jiu-jitsu", 5);
		default -> {
		}
		}

		switch (perfil.getObjetivo()) {
		case "DEFESA_PESSOAL" -> adicionar(pontos, 1, "judo", "karate", "jiu-jitsu", "muay-thai");
		case "CONDICIONAMENTO" -> adicionar(pontos, 2, "taekwondo", "muay-thai", "boxe");
		case "DISCIPLINA" -> adicionar(pontos, 2, "judo", "karate", "taekwondo");
		case "COMPETICAO" -> adicionar(pontos, 1,
				"judo", "karate", "taekwondo", "jiu-jitsu", "muay-thai", "boxe");
		default -> throw new IllegalArgumentException("Objetivo de recomendacao invalido.");
		}

		List<MartialArt> classificacao = martialArtService.listarTodas().stream()
				.sorted(Comparator.<MartialArt>comparingInt(arte -> pontos.get(arte.slug()))
						.reversed()
						.thenComparing(MartialArt::nome))
				.toList();
		MartialArt principal = classificacao.get(0);
		return new Resultado(principal, classificacao.subList(1, classificacao.size()),
				pontos.get(principal.slug()), observacoes(perfil));
	}

	private void validarOpcao(String campo, String valor, Set<String> permitidos,
			Map<String, String> erros) {
		if (valor == null || !permitidos.contains(valor)) {
			erros.put(campo, "Escolha uma das opcoes disponiveis.");
		}
	}

	private void adicionar(Map<String, Integer> pontos, String slug, int valor) {
		pontos.computeIfPresent(slug, (chave, atual) -> atual + valor);
	}

	private void adicionar(Map<String, Integer> pontos, int valor, String... slugs) {
		for (String slug : slugs) {
			adicionar(pontos, slug, valor);
		}
	}

	private List<String> observacoes(PerfilRecomendacao perfil) {
		List<String> observacoes = new ArrayList<>();
		observacoes.add("A sugestao considera principalmente sua preferencia tecnica e seu objetivo; experiencia e contato ajudam a orientar a primeira aula.");
		if (perfil.getIdade() < 18) {
			observacoes.add("Para menores de 18 anos, procure uma turma adequada a idade e converse com um responsavel.");
		} else if (perfil.getIdade() >= 60) {
			observacoes.add("A idade nao impede a pratica; converse com o instrutor sobre ritmo, adaptacoes e progressao.");
		}
		observacoes.add("Altura e peso nao determinam a arte ideal; informe esses dados ao instrutor para orientar pareamento e adaptacoes.");
		observacoes.add("Genero nao altera a pontuacao: escolha uma turma e um ambiente em que voce se sinta acolhido.");
		if ("INICIANTE".equals(perfil.getExperiencia())) {
			observacoes.add("Como iniciante, priorize uma aula experimental com fundamentos e acompanhamento proximo.");
		} else if ("AVANCADO".equals(perfil.getExperiencia())) {
			observacoes.add("Considere assistir a uma aula e conversar com o professor sobre seu nivel e objetivos tecnicos.");
		}
		if ("LEVE".equals(perfil.getContato())) {
			observacoes.add("Voce prefere contato leve; confirme com a academia como controla o contato nos treinos.");
		} else if ("INTENSO".equals(perfil.getContato())) {
			observacoes.add("Treinos intensos variam por academia; confirme a progressao e as medidas de seguranca da turma.");
		}
		if (perfil.isPossuiRestricao()) {
			observacoes.add("Converse com o instrutor e, se necessario, com um profissional de saude antes de iniciar ou intensificar o treino.");
		}
		return List.copyOf(observacoes);
	}

	public record Resultado(
			MartialArt principal,
			List<MartialArt> alternativas,
			int pontuacao,
			List<String> observacoes) {
	}
}
