package com.artes.marciais.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.artes.marciais.demo.model.AtributosLuta;
import com.artes.marciais.demo.model.MartialArt;
import com.artes.marciais.demo.repository.AtributosLutaRepository;

/**
 * Simula qual arte sairia de um confronto contra a outra.
 *
 * O modelo e deliberadamente didatico: compara os atributos de cada arte e
 * pondera tres fatores de distancia (golpes a longa distancia, clinch e
 * trabalho no chão). Nao substitui treino, preparacao ou seguranca.
 */
@Service
public class SimulacaoService {

	/** Diferenca minima de pontos para considerar vantagem em vez de empate. */
	private static final int MARGEM_DE_VITORIA = 4;

	private final MartialArtService martialArtService;
	private final AtributosLutaRepository atributosRepository;

	public SimulacaoService(MartialArtService martialArtService,
			AtributosLutaRepository atributosRepository) {
		this.martialArtService = martialArtService;
		this.atributosRepository = atributosRepository;
	}

	public List<AtributosLuta> listarAtributos() {
		return atributosRepository.findAll();
	}

	public Optional<AtributosLuta> buscarAtributos(String slug) {
		return atributosRepository.findBySlug(slug);
	}

	/**
	 * @throws IllegalArgumentException se um dos slugs nao existir no catalogo
	 * @throws DueloInvalidoException   se as duas artes forem a mesma
	 */
	public Resultado simular(String slugPrimeira, String slugSegunda) {
		AtributosLuta primeira = buscarAtributos(slugPrimeira)
				.orElseThrow(() -> new IllegalArgumentException("Arte desconhecida: " + slugPrimeira));
		AtributosLuta segunda = buscarAtributos(slugSegunda)
				.orElseThrow(() -> new IllegalArgumentException("Arte desconhecida: " + slugSegunda));
		if (primeira.slug().equals(segunda.slug())) {
			throw new DueloInvalidoException("Escolha duas artes diferentes para simular o duelo.");
		}

		int pontosPrimeira = pontuar(primeira, segunda);
		int pontosSegunda = pontuar(segunda, primeira);

		MartialArt artePrimeira = martialArtService.buscarPorSlug(primeira.slug()).orElseThrow();
		MartialArt arteSegunda = martialArtService.buscarPorSlug(segunda.slug()).orElseThrow();

		return new Resultado(artePrimeira, arteSegunda, primeira, segunda,
				pontosPrimeira, pontosSegunda, definirVencedor(pontosPrimeira, pontosSegunda),
				distanciaFavoravel(primeira, segunda), distanciasPadrao(), explicacoes(primeira, segunda));
	}

	/**
	 * Pontua uma arte contra a outra: parte da soma de atributos e aplica os
	 * ajustes de distancia, clinch e trabalho no chão.
	 */
	private int pontuar(AtributosLuta atacante, AtributosLuta defensor) {
		int pontos = atacante.total();

		// Distancia: golpes valem mais contra quem trabalha de perto.
		pontos += (atacante.alcance() - defensor.alcance()) * 2;

		// Clinch: quem domina o corpo a corpo leva a luta para dentro.
		pontos += (atacante.clinch() - defensor.clinch()) * 2;

		// Chao: quem domina o solo converte a queda em vantagem longa.
		pontos += (atacante.chao() - defensor.chao()) * 2;

		// Quem defende mal sofre o golpe forte do adversario.
		pontos += (atacante.golpes() - defensor.defesa());

		// Controle: amarrar o oponente neutraliza a agressividade dele.
		pontos += (atacante.controle() - defensor.agressividade());

		return pontos;
	}

	/** Faixas de distancia usadas para explicar o resultado ao usuario. */
	public List<Distancia> distanciasPadrao() {
		return List.of(
				new Distancia("Longa", "Somente socos, sem chutar, como no boxe esportivo."),
				new Distancia("Média", "Golpes de mão e perna sem entrar no clinch."),
				new Distancia("Corpo a corpo", "Cotovelos, joelhos e domínio do clinch."),
				new Distancia("No chão", "Alavancas, posições e submissão."));
	}

	private ResultadoVencedor definirVencedor(int pontosPrimeira, int pontosSegunda) {
		int diferenca = pontosPrimeira - pontosSegunda;
		if (Math.abs(diferenca) < MARGEM_DE_VITORIA) {
			return ResultadoVencedor.EMPATE;
		}
		return diferenca > 0 ? ResultadoVencedor.PRIMEIRA : ResultadoVencedor.SEGUNDA;
	}
/** Explica qual arte consegue trabalhar na distancia que mais favorece. */
	private String distanciaFavoravel(AtributosLuta primeira, AtributosLuta segunda) {
		if (primeira.alcance() > segunda.alcance()) {
			return primeira.slug() + " trabalha melhor a distância longa e pode manter o combate no lado dele.";
		}
		if (segunda.alcance() > primeira.alcance()) {
			return segunda.slug() + " trabalha melhor a distância longa e pode manter o combate no lado dele.";
		}
		return "As duas artes operam na mesma distancia, entao o duelo tende a ser equilibrado.";
	}

	/**
	 * Monta os conselhos dos dois lados, sempre mostrando como o lado em
	 * desvantagem pode evitar a derrota.
	 */
	private List<String> explicacoes(AtributosLuta primeira, AtributosLuta segunda) {
		List<String> conselhos = new ArrayList<>();

		if (primeira.chao() >= segunda.chao()) {
			conselhos.add(primeira.slug() + " leva vantagem se conseguir derrubar e levar a luta para o chão.");
			conselhos.add(segunda.slug() + " precisa evitar a queda e manter o combate em pe.");
		} else {
			conselhos.add(segunda.slug() + " leva vantagem se conseguir derrubar e levar a luta para o chão.");
			conselhos.add(primeira.slug() + " precisa evitar a queda e manter o combate em pe.");
		}

		if (primeira.golpes() >= segunda.golpes()) {
			conselhos.add("No golpe direto, " + primeira.slug() + " tem a vantagem.");
		} else {
			conselhos.add("No golpe direto, " + segunda.slug() + " tem a vantagem.");
		}

		if (primeira.resistencia() > segunda.resistencia()) {
			conselhos.add("Em combate longo, " + primeira.slug() + " aguenta melhor o ritmo.");
		} else if (segunda.resistencia() > primeira.resistencia()) {
			conselhos.add("Em combate longo, " + segunda.slug() + " aguenta melhor o ritmo.");
		}

		conselhos.add("Treino consistente pode inverter qualquer resultado desta simulação.");
		return List.copyOf(conselhos);
	}

	/** Resultado possivel de um duelo simulado. */
	public enum ResultadoVencedor {
		PRIMEIRA("Vantagem da primeira arte"),
		SEGUNDA("Vantagem da segunda arte"),
		EMPATE("Duelo equilibrado");

		private final String rotulo;

		ResultadoVencedor(String rotulo) {
			this.rotulo = rotulo;
		}

		public String getRotulo() {
			return rotulo;
		}
	}

	/** Erro de uso da simulacao que deve virar mensagem no formulario. */
	public static class DueloInvalidoException extends RuntimeException {

		public DueloInvalidoException(String mensagem) {
			super(mensagem);
		}
	}

	public record Resultado(
			MartialArt artePrimeira,
			MartialArt arteSegunda,
			AtributosLuta atributosPrimeira,
			AtributosLuta atributosSegunda,
			int pontosPrimeira,
			int pontosSegunda,
			ResultadoVencedor vencedor,
			String distanciaFavoravel,
			List<Distancia> distancias,
			List<String> conselhos) {
	}

	/** Uma faixa de distancia e o que ela permite em um combate. */
	public record Distancia(String nome, String descricao) {
	}
}