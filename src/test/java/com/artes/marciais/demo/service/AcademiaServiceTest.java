package com.artes.marciais.demo.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.artes.marciais.demo.model.Academia;
import com.artes.marciais.demo.repository.AcademiaRepository;
import com.artes.marciais.demo.repository.MartialArtRepository;

/**
 * Testes do catalogo de academias e dos filtros do mapa.
 */
class AcademiaServiceTest {

	private AcademiaService service;

	@BeforeEach
	void setUp() {
		MartialArtRepository artes = new MartialArtRepository.InMemory();
		service = new AcademiaService(new AcademiaRepository.InMemory(),
				new MartialArtService(artes));
	}

	@Nested
	@DisplayName("catalogo")
	class Catalogo {

		@Test
		void catalogoNaoEstaVazio() {
			assertThat(service.listarTodas()).isNotEmpty();
		}

		@Test
		void slugsSaoUnicos() {
			assertThat(service.listarTodas())
					.extracting(Academia::slug)
					.doesNotHaveDuplicates();
		}

		@Test
		void coordenadasEstaoNaGrandeSaoPaulo() {
			for (Academia a : service.listarTodas()) {
				assertThat(a.latitude()).isBetween(-24.5, -21.5);
				assertThat(a.longitude()).isBetween(-49.0, -44.0);
			}
		}

		@Test
		void todoRegistroDoExemploEstaMarcadoComoExemplo() {
			assertThat(service.listarTodas()).allMatch(Academia::exemplo);
		}

		@Test
		void todaAcademiaEnsinaPeloMenosUmaArteDoCatalogo() {
			List<String> slugsArtes = new MartialArtRepository.InMemory().findAll().stream()
					.map(a -> a.slug()).toList();

			for (Academia academia : service.listarTodas()) {
				assertThat(academia.artes()).isNotEmpty();
				assertThat(academia.artes()).allMatch(slugsArtes::contains);
			}
		}

		@Test
		void todaAcademiaTemNomeEBairro() {
			for (Academia a : service.listarTodas()) {
				assertThat(a.nome()).isNotBlank();
				assertThat(a.bairro()).isNotBlank();
				assertThat(a.googleMapsUrl()).contains("google.com/maps");
			}
		}

		@Test
		void buscaPorSlugFunciona() {
			assertThat(service.buscarPorSlug("dojo-bela-vista")).isPresent();
			assertThat(service.buscarPorSlug("inexistente")).isEmpty();
		}
	}

	@Nested
	@DisplayName("filtros")
	class Filtros {

		@Test
		void semFiltroRetornaTodas() {
			assertThat(service.listarPorArtes(List.of())).hasSameSizeAs(service.listarTodas());
			assertThat(service.listarPorArtes(null)).hasSameSizeAs(service.listarTodas());
		}

		@ParameterizedTest
		@ValueSource(strings = { "judo", "jiu-jitsu", "boxe", "muay-thai", "karate", "taekwondo" })
		void filtroPorArteRetornaSomenteQuemEnsina(String arte) {
			List<Academia> resultado = service.listarPorArtes(List.of(arte));

			assertThat(resultado).isNotEmpty();
			assertThat(resultado).allMatch(a -> a.ensina(arte));
		}

		@Test
		void arteDesconhecidaNaoQuebraEFiltraTudo() {
			assertThat(service.listarPorArtes(List.of("inexistente"))).isEmpty();
		}

		@Test
		void filtroComVariasArtesUneOsResultados() {
			List<Academia> soJudo = service.listarPorArtes(List.of("judo"));
			List<Academia> soBoxe = service.listarPorArtes(List.of("boxe"));

			List<Academia> ambas = service.listarPorArtes(List.of("judo", "boxe"));

			assertThat(ambas).hasSize(soJudo.size() + soBoxe.size());
		}

		@Test
		void cidadeTodasDevolveOCatalogoInteiro() {
			assertThat(service.listarPorCidade("todas")).hasSameSizeAs(service.listarTodas());
			assertThat(service.listarPorCidade(null)).hasSameSizeAs(service.listarTodas());
		}

		@Test
		void filtroPorCidadeFunca() {
			List<Academia> resultado = service.listarPorCidade("Osasco");

			assertThat(resultado).isNotEmpty();
			assertThat(resultado).allMatch(a -> a.bairro().contains("Osasco"));
		}

		@Test
		void cidadeDesconhecidaDevolveListaVazia() {
			assertThat(service.listarPorCidade("NaoExiste")).isEmpty();
		}

		@Test
		void listaDeCidadesNaoTemRepetidosNemVazios() {
			assertThat(service.cidades()).doesNotHaveDuplicates();
			assertThat(service.cidades()).allSatisfy(c -> assertThat(c).isNotBlank());
		}

		@Test
		void artesComAcademiaCorrespondemAoCatalogoDeArtes() {
			List<String> slugsArtes = new MartialArtRepository.InMemory().findAll().stream()
					.map(a -> a.slug()).toList();

			assertThat(service.artesComAcademia()).allMatch(slugsArtes::contains);
		}
	}
}