package com.artes.marciais.demo.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import com.artes.marciais.demo.model.Contato;
import com.artes.marciais.demo.model.Experiencia;
import com.artes.marciais.demo.model.Genero;
import com.artes.marciais.demo.model.MartialArt;
import com.artes.marciais.demo.model.Objetivo;
import com.artes.marciais.demo.model.PerfilRecomendacao;
import com.artes.marciais.demo.model.Preferencia;

@Service
public class RecomendacaoService {

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
		validarOpcao("genero", perfil.getGenero(), RecomendacaoService::generoValido, erros);
		validarOpcao("objetivo", perfil.getObjetivo(), RecomendacaoService::objetivoValido, erros);
		validarOpcao("preferencia", perfil.getPreferencia(), e -> Preferencia.de(e) != Preferencia.SEM_PREFERENCIA
				|| "SEM_PREFERENCIA".equalsIgnoreCase(e.trim()), erros);
		validarOpcao("experiencia", perfil.getExperiencia(), RecomendacaoService::experienciaValida, erros);
		validarOpcao("contato", perfil.getContato(), RecomendacaoService::contatoValido, erros);
		return erros;
	}

	private static boolean objetivoValido(String valor) {
		return objetivoDe(valor) != null;
	}

	private static boolean generoValido(String valor) {
		String normalizado = valor.trim().toUpperCase(Locale.ROOT);
		for (Genero genero : Genero.values()) {
			if (genero.name().equals(normalizado)) {
				return true;
			}
		}
		return false;
	}

	/** Converte o texto do formulario no objetivo, ou {@code null} se invalido. */
	private static Objetivo objetivoDe(String valor) {
		if (valor == null) {
			return null;
		}
		String normalizado = valor.trim().toUpperCase(Locale.ROOT);
		for (Objetivo objetivo : Objetivo.values()) {
			if (objetivo.name().equals(normalizado)) {
				return objetivo;
			}
		}
		return null;
	}

	private static boolean experienciaValida(String valor) {
		String normalizado = valor.trim().toUpperCase(Locale.ROOT);
		for (Experiencia experiencia : Experiencia.values()) {
			if (experiencia.name().equals(normalizado)) {
				return true;
			}
		}
		return false;
	}

	private static boolean contatoValido(String valor) {
		String normalizado = valor.trim().toUpperCase(Locale.ROOT);
		for (Contato contato : Contato.values()) {
			if (contato.name().equals(normalizado)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Confere se a opcao enviada existe na lista do formulario. Valores vazios
	 * e desconhecidos viram mensagem de erro em vez de excecao.
	 */
	private void validarOpcao(String campo, String valor, Predicate<String> permitido,
			Map<String, String> erros) {
		if (valor == null || valor.isBlank() || !permitido.test(valor)) {
			erros.put(campo, "Escolha uma das opcoes disponiveis.");
		}
	}

	/**
	 * Pontua cada arte conforme preferencia e objetivo e devolve a melhor,
	 * com as demais como alternativas.
	 *
	 * @throws NoSuchElementException se o catalogo estiver vazio, o que
	 *         significaria que nao ha arte para recomendar
	 * @throws IllegalArgumentException se o objetivo for desconhecido
	 */
	public Resultado recomendar(PerfilRecomendacao perfil) {
		List<MartialArt> artes = martialArtService.listarTodas();
		if (artes.isEmpty()) {
			throw new NoSuchElementException("O catalogo de artes esta vazio.");
		}

		Map<String, Integer> pontos = new LinkedHashMap<>();
		for (MartialArt arte : artes) {
			pontos.put(arte.slug(), 0);
		}

		aplicarPreferencia(perfil, pontos);
		aplicarObjetivo(perfil, pontos);

		List<MartialArt> classificacao = artes.stream()
				.sorted(Comparator.<MartialArt>comparingInt(arte -> pontos.get(arte.slug()))
						.reversed()
						.thenComparing(MartialArt::nome))
				.toList();
		MartialArt principal = classificacao.get(0);
		return new Resultado(principal, classificacao.subList(1, classificacao.size()),
				pontos.get(principal.slug()), observacoes(perfil));
	}

	private void aplicarPreferencia(PerfilRecomendacao perfil, Map<String, Integer> pontos) {
		Preferencia preferencia = Preferencia.de(perfil.getPreferencia());
		if (preferencia.getArteFavorita() != null) {
			adicionar(pontos, preferencia.getArteFavorita(), preferencia.getPontos());
		}
	}

	private void aplicarObjetivo(PerfilRecomendacao perfil, Map<String, Integer> pontos) {
		Objetivo objetivo = objetivoDe(perfil.getObjetivo());
		if (objetivo == null) {
			throw new IllegalArgumentException("Objetivo de recomendacao invalido.");
		}
		for (String slug : objetivo.getArtes()) {
			adicionar(pontos, slug, objetivo.getPontos());
		}
	}

	private void adicionar(Map<String, Integer> pontos, String slug, int valor) {
		pontos.computeIfPresent(slug, (chave, atual) -> atual + valor);
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
		if (Experiencia.INICIANTE == Experiencia.de(perfil.getExperiencia())) {
			observacoes.add("Como iniciante, priorize uma aula experimental com fundamentos e acompanhamento proximo.");
		} else if (Experiencia.AVANCADO == Experiencia.de(perfil.getExperiencia())) {
			observacoes.add("Considere assistir a uma aula e conversar com o professor sobre seu nivel e objetivos tecnicos.");
		}
		if (Contato.LEVE == Contato.de(perfil.getContato())) {
			observacoes.add("Voce prefere contato leve; confirme com a academia como controla o contato nos treinos.");
		} else if (Contato.INTENSO == Contato.de(perfil.getContato())) {
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
