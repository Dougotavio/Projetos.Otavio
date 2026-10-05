package com.artes.marciais.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.artes.marciais.demo.model.AtributosLuta;
import com.artes.marciais.demo.repository.AtributosLutaRepository;
import com.artes.marciais.demo.repository.MartialArtRepository;
import com.artes.marciais.demo.service.SimulacaoService.Resultado;
import com.artes.marciais.demo.service.SimulacaoService.ResultadoVencedor;

/**
 * Testes da simulacao de duelo: catalogo de atributos, pontuacao, simetria
 * entre os lados e tratamento de entradas invalidas.
 */
class SimulacaoServiceTest {

	private SimulacaoService service;

	@BeforeEach
	void setUp() {
		MartialArtRepository artes = new MartialArtRepository.InMemory();
		service = new SimulacaoService(new MartialArtService(artes),
				new AtributosLutaRepository.InMemory());
	}

	@Nested
	@DisplayName("atributos")
	class Atributos {

		@Test
		void todoAtributoEstaNaEscalaDeZeroACinco() {
			for (AtributosLuta a : service.listarAtributos()) {
				assertThat(a.slug()).isNotBlank();
				assertThat(a.golpes()).isBetween(0, 5);
				assertThat(a.defesa()).isBetween(0, 5);
				assertThat(a.projecao()).isBetween(0, 5);
				assertThat(a.clinch()).isBetween(0, 5);
				assertThat(a.chao()).isBetween(0, 5);
				assertThat(a.controle()).isBetween(0, 5);
				assertThat(a.resistencia()).isBetween(0, 5);
				assertThat(a.agressividade()).isBetween(0, 5);
				assertThat(a.alcance()).isBetween(1, 3);
			}
		}

		@Test
		void todoAtributoTemArteCorrespondenteNoCatalogo() {
			List<String> slugs = new MartialArtRepository.InMemory().findAll().stream()
					.map(a -> a.slug()).toList();

			assertThat(service.listarAtributos())
					.extracting(AtributosLuta::slug)
					.containsExactlyInAnyOrderElementsOf(slugs);
		}

		@Test
		void cadaArteTemPerfilDescrito() {
			for (AtributosLuta a : service.listarAtributos()) {
				assertThat(a.resumoPerfil()).contains("alcance ideal");
				assertThat(a.total()).isPositive();
			}
		}

		@Test
		void buscaPorSlugFunciona() {
			assertThat(service.buscarAtributos("judo")).isPresent();
			assertThat(service.buscarAtributos("inexistente")).isEmpty();
		}
	}

	@Nested
	@DisplayName("simular")
	class Simular {

		@Test
		void dueloValidoDevolveResultadoCompleto() {
			Resultado resultado = service.simular("judo", "boxe");

			assertThat(resultado.artePrimeira().slug()).isEqualTo("judo");
			assertThat(resultado.arteSegunda().slug()).isEqualTo("boxe");
			assertThat(resultado.vencedor()).isNotNull();
			assertThat(resultado.conselhos()).isNotEmpty();
			assertThat(resultado.distancias()).hasSize(4);
		}

		@Test
		void inverterOsLadosInverteVencedorOuPreservaEmpate() {
			Resultado frente = service.simular("judo", "boxe");
			Resultado verso = service.simular("boxe", "judo");

			assertThat(verso.pontosPrimeira()).isEqualTo(frente.pontosSegunda());
			assertThat(verso.pontosSegunda()).isEqualTo(frente.pontosPrimeira());

			if (frente.vencedor() == ResultadoVencedor.EMPATE) {
				assertThat(verso.vencedor()).isEqualTo(ResultadoVencedor.EMPATE);
			} else if (frente.vencedor() == ResultadoVencedor.PRIMEIRA) {
				assertThat(verso.vencedor()).isEqualTo(ResultadoVencedor.SEGUNDA);
			} else {
				assertThat(verso.vencedor()).isEqualTo(ResultadoVencedor.PRIMEIRA);
			}
		}

		@ParameterizedTest
		@CsvSource({
			"jiu-jitsu, boxe",
			"judo, muay-thai",
			"boxe, taekwondo",
			"karate, jiu-jitsu"
		})
		void nenhumaCombinacaoQuebra(String primeira, String segunda) {
			Resultado resultado = service.simular(primeira, segunda);

			assertThat(resultado.pontosPrimeira()).isNotEqualTo(resultado.pontosSegunda());
			assertThat(resultado.conselhos()).isNotEmpty();
		}

		@Test
		void solDaChaoLevaVantagemContraArteDeGolpe() {
			Resultado resultado = service.simular("jiu-jitsu", "boxe");

			assertThat(resultado.vencedor()).isEqualTo(ResultadoVencedor.PRIMEIRA);
			assertThat(resultado.conselhos())
					.anySatisfy(c -> assertThat(c).contains("evitar a queda"));
		}

		@Test
		void alcanceMaiorPermiteControlarODuelo() {
			Resultado longaDistancia = service.simular("boxe", "jiu-jitsu");
			Resultado curtaDistancia = service.simular("jiu-jitsu", "boxe");

			assertThat(longaDistancia.distanciaFavoravel()).contains("boxe");
			assertThat(curtaDistancia.distanciaFavoravel()).contains("boxe");
		}

		@Test
		void mesmaArteLancaErroDeDuelo() {
			assertThatThrownBy(() -> service.simular("judo", "judo"))
					.isInstanceOf(SimulacaoService.DueloInvalidoException.class)
					.hasMessageContaining("duas artes diferentes");
		}

		@Test
		void arteInexistenteLancaErroDeArgumento() {
			assertThatThrownBy(() -> service.simular("judo", "inexistente"))
					.isInstanceOf(IllegalArgumentException.class);
		}

		@Test
		void distanciasPadraoSempreTemQuatroFaixas() {
			assertThat(service.distanciasPadrao()).hasSize(4);
			assertThat(service.distanciasPadrao())
					.allSatisfy(d -> {
						assertThat(d.nome()).isNotBlank();
						assertThat(d.descricao()).isNotBlank();
					});
		}
	}
}