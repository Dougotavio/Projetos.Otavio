package com.artes.marciais.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.artes.marciais.demo.model.Contato;
import com.artes.marciais.demo.model.Experiencia;
import com.artes.marciais.demo.model.Genero;
import com.artes.marciais.demo.model.MartialArt;
import com.artes.marciais.demo.model.Objetivo;
import com.artes.marciais.demo.model.PerfilRecomendacao;
import com.artes.marciais.demo.model.Preferencia;
import com.artes.marciais.demo.repository.MartialArtRepository;
import com.artes.marciais.demo.service.RecomendacaoService.Resultado;

/**
 * Testes unitarios do servico de recomendacao: validacao do perfil,
 * pontuacao por preferencia/objetivo, ordenacao e observacoes.
 */
class RecomendacaoServiceTest {

	private RecomendacaoService service;
	private List<MartialArt> catalogo;

	@BeforeEach
	void setUp() {
		MartialArtRepository repository = new MartialArtRepository.InMemory();
		service = new RecomendacaoService(new MartialArtService(repository));
		catalogo = repository.findAll();
	}

	@Nested
	@DisplayName("enums")
	class Enums {

		@Test
		void todoEnumTemRotuloPreenchido() {
			for (Objetivo objetivo : Objetivo.values()) {
				assertThat(objetivo.getRotulo()).isNotBlank();
			}
			for (Preferencia preferencia : Preferencia.values()) {
				assertThat(preferencia.getRotulo()).isNotBlank();
			}
			for (Genero genero : Genero.values()) {
				assertThat(genero.getRotulo()).isNotBlank();
			}
			for (Experiencia experiencia : Experiencia.values()) {
				assertThat(experiencia.getRotulo()).isNotBlank();
			}
			for (Contato contato : Contato.values()) {
				assertThat(contato.getRotulo()).isNotBlank();
			}
		}

		@Test
		void todaPreferenciaComPontosApontaParaUmaArteDoCatalogo() {
			List<String> slugs = catalogo.stream().map(MartialArt::slug).toList();

			for (Preferencia preferencia : Preferencia.values()) {
				if (preferencia != Preferencia.SEM_PREFERENCIA) {
					assertThat(preferencia.getArteFavorita())
							.as("arte de %s", preferencia)
							.isIn(slugs);
					assertThat(preferencia.getPontos()).isPositive();
				}
			}
		}

		@Test
		void todoObjetivoApontaParaArtesConhecidas() {
			List<String> slugs = catalogo.stream().map(MartialArt::slug).toList();

			for (Objetivo objetivo : Objetivo.values()) {
				assertThat(objetivo.getArtes()).isNotEmpty();
				assertThat(objetivo.getArtes()).allMatch(slugs::contains);
			}
		}

		@ParameterizedTest
		@ValueSource(strings = { "ROBOTICA", "MULHER", "" })
		void generoDesconhecidoCaiNoPadrao(String valor) {
			Genero resolvido = Genero.de(valor);

			assertThat(resolvido).isNotNull();
		}

		@Test
		void generoValidoEResolvidoPeloNome() {
			assertThat(Genero.de("MULHER")).isEqualTo(Genero.MULHER);
			assertThat(Genero.de("  mulher ")).isEqualTo(Genero.MULHER);
			assertThat(Genero.de("ROBOTICA")).isEqualTo(Genero.PREFIRO_NAO_INFORMAR);
			assertThat(Genero.de(null)).isEqualTo(Genero.PREFIRO_NAO_INFORMAR);
		}

		@Test
		void preferenciaDesconhecidaNaoLancaExcecao() {
			assertThat(Preferencia.de("TELEPORTACAO")).isEqualTo(Preferencia.SEM_PREFERENCIA);
			assertThat(Preferencia.de(null)).isEqualTo(Preferencia.SEM_PREFERENCIA);
		}

		@Test
		void catalogoVazioLancaExcecaoClara() {
			MartialArtRepository vazio = new MartialArtRepository() {
				@Override
				public List<MartialArt> findAll() {
					return List.of();
				}

				@Override
				public java.util.Optional<MartialArt> findBySlug(String slug) {
					return java.util.Optional.empty();
				}
			};
			RecomendacaoService serviceVazio = new RecomendacaoService(new MartialArtService(vazio));

			assertThatThrownBy(() -> serviceVazio.recomendar(perfilValido()))
					.isInstanceOf(java.util.NoSuchElementException.class);
		}
	}

	@Nested
	@DisplayName("validar")
	class Validar {

		@Test
		void aceitaPerfilCompletoValido() {
			assertThat(service.validar(perfilValido())).isEmpty();
		}

		@ParameterizedTest
		@ValueSource(ints = { 5, 101 })
		void rejeitaIdadeForaDaFaixa(int idade) {
			PerfilRecomendacao perfil = perfilValido();
			perfil.setIdade(idade);

			assertThat(service.validar(perfil)).containsKey("idade");
		}

		@ParameterizedTest
		@ValueSource(ints = { 6, 100 })
		void aceitaLimitesDeIdade(int idade) {
			PerfilRecomendacao perfil = perfilValido();
			perfil.setIdade(idade);

			assertThat(service.validar(perfil)).doesNotContainKey("idade");
		}

		@Test
		void rejeitaIdadeAusente() {
			PerfilRecomendacao perfil = perfilValido();
			perfil.setIdade(null);

			assertThat(service.validar(perfil)).containsKey("idade");
		}

		@ParameterizedTest
		@ValueSource(ints = { 79, 231 })
		void rejeitaAlturaForaDaFaixa(int alturaCm) {
			PerfilRecomendacao perfil = perfilValido();
			perfil.setAlturaCm(alturaCm);

			assertThat(service.validar(perfil)).containsKey("alturaCm");
		}

		@ParameterizedTest
		@ValueSource(doubles = { 14.9, 300.1 })
		void rejeitaPesoForaDaFaixa(double pesoKg) {
			PerfilRecomendacao perfil = perfilValido();
			perfil.setPesoKg(pesoKg);

			assertThat(service.validar(perfil)).containsKey("pesoKg");
		}

		@Test
		void rejeitaPesoNaoFinito() {
			PerfilRecomendacao perfil = perfilValido();
			perfil.setPesoKg(Double.POSITIVE_INFINITY);

			assertThat(service.validar(perfil)).containsKey("pesoKg");
		}

		@ParameterizedTest
		@ValueSource(strings = { "genero", "objetivo", "preferencia", "experiencia", "contato" })
		void rejeitaOpcaoNaoReconhecida(String campo) {
			PerfilRecomendacao perfil = perfilValido();
			switch (campo) {
			case "genero" -> perfil.setGenero("ROBOTICA");
			case "objetivo" -> perfil.setObjetivo("VIRAR_SUPER_HEROI");
			case "preferencia" -> perfil.setPreferencia("TELEPORTACAO");
			case "experiencia" -> perfil.setExperiencia("MESTRE");
			default -> perfil.setContato("BRUTAL");
			}

			assertThat(service.validar(perfil)).containsKey(campo);
		}

		@Test
		void aceitaValoresPadraoDePerfilEmBranco() {
			PerfilRecomendacao perfil = new PerfilRecomendacao();
			perfil.setIdade(30);
			perfil.setAlturaCm(165);
			perfil.setPesoKg(60.0);

			assertThat(service.validar(perfil)).isEmpty();
		}

		@Test
		void acumulaTodosOsErrosDeUmaVez() {
			PerfilRecomendacao perfil = new PerfilRecomendacao();
			perfil.setGenero("X");
			perfil.setContato(null);

			assertThat(service.validar(perfil))
					.containsKeys("idade", "alturaCm", "pesoKg", "genero", "contato");
		}
	}

	@Nested
	@DisplayName("recomendar")
	class Recomendar {

		@ParameterizedTest
		@ValueSource(strings = { "CHUTES", "GOLPES_VARIADOS", "ATAQUE_AGRESSIVO", "DEFESA_CONTROLE",
				"PROJECOES", "LUTA_NO_SOLO", "SEM_PREFERENCIA" })
		void todaPreferenciaGeraUmaClassificacaoCompleta(String preferencia) {
			Resultado resultado = service.recomendar(comPreferencia(preferencia));

			assertThat(todos(resultado)).hasSize(catalogo.size());
			assertThat(resultado.pontuacao()).isPositive();
			assertThat(resultado.principal()).isNotNull();
		}

		@Test
		void chutesLevamParaTaekwondo() {
			assertThat(service.recomendar(comPreferencia("CHUTES")).principal().slug())
					.isEqualTo("taekwondo");
		}

		@Test
		void projecoesLevamParaJudo() {
			assertThat(service.recomendar(comPreferencia("PROJECOES")).principal().slug())
					.isEqualTo("judo");
		}

		@Test
		void defesaNoChaoLevaParaJiuJitsu() {
			assertThat(service.recomendar(comPreferencia("LUTA_NO_SOLO")).principal().slug())
					.isEqualTo("jiu-jitsu");
		}

		@Test
		void semPreferenciaUsaSomenteOObjetivo() {
			Resultado resultado = service.recomendar(comPreferencia("SEM_PREFERENCIA"));

			assertThat(resultado.principal().slug()).isNotBlank();
			assertThat(resultado.pontuacao()).isEqualTo(2);
		}

		@Test
		void classificacaoNaoRepeteNemOmiteArtes() {
			Resultado resultado = service.recomendar(comPreferencia("CHUTES"));

			assertThat(todos(resultado))
					.extracting(MartialArt::slug)
					.containsExactlyInAnyOrderElementsOf(
							catalogo.stream().map(MartialArt::slug).toList());
		}

		@Test
		void empatesSaoDesempatadosPeloNome() {
			Resultado resultado = service.recomendar(comPreferencia("SEM_PREFERENCIA"));

			// Todas as artes sem pontuacao (jiu-jitsu, judo, karate) ficam no fim,
			// ordenadas alfabeticamente pelo nome.
			List<String> semPontuacao = resultado.alternativas().stream()
					.map(MartialArt::slug)
					.toList();

			assertThat(semPontuacao).endsWith("jiu-jitsu", "judo", "karate");
			assertThat(resultado.principal().slug()).isEqualTo("boxe");
		}

		@Test
		void generoNaoAlteraARecomendacao() {
			String principalOriginal = service.recomendar(comPreferencia("CHUTES")).principal().slug();

			for (String genero : List.of("MULHER", "HOMEM", "NAO_BINARIO", "PREFIRO_NAO_INFORMAR")) {
				PerfilRecomendacao copia = comPreferencia("CHUTES");
				copia.setGenero(genero);

				assertThat(service.recomendar(copia).principal().slug()).isEqualTo(principalOriginal);
			}
		}

		@Test
		void alturaPesoEIdadeNaoAlteramARecomendacao() {
			String principalOriginal = service.recomendar(comPreferencia("PROJECOES")).principal().slug();

			PerfilRecomendacao outro = comPreferencia("PROJECOES");
			outro.setAlturaCm(140);
			outro.setPesoKg(45.0);
			outro.setIdade(62);

			assertThat(service.recomendar(outro).principal().slug()).isEqualTo(principalOriginal);
		}

		@Test
		void objetivoInvalidoLancaExcecao() {
			PerfilRecomendacao perfil = comPreferencia("CHUTES");
			perfil.setObjetivo("OBJETIVO_DESCONHECIDO");

			assertThatThrownBy(() -> service.recomendar(perfil))
					.isInstanceOf(IllegalArgumentException.class);
		}
	}

	@Nested
	@DisplayName("observacoes")
	class Observacoes {

		@Test
		void sempreExplicaQueGeneroNaoConta() {
			assertThat(texto(service.recomendar(comPreferencia("CHUTES")).observacoes()))
					.anyMatch(o -> o.contains("genero"));
		}

		@Test
		void sempreExplicaQueAlturaEPesoNaoDefinemAArte() {
			assertThat(texto(service.recomendar(comPreferencia("CHUTES")).observacoes()))
					.anyMatch(o -> o.contains("altura e peso"));
		}

		@Test
		void inicianteRecebeDicaDeAulaExperimental() {
			PerfilRecomendacao perfil = comPreferencia("CHUTES");
			perfil.setExperiencia("INICIANTE");

			assertThat(texto(service.recomendar(perfil).observacoes()))
					.anyMatch(o -> o.contains("aula experimental"));
		}

		@Test
		void avancadoRecebeDicaSobreNivel() {
			PerfilRecomendacao perfil = comPreferencia("CHUTES");
			perfil.setExperiencia("AVANCADO");

			assertThat(texto(service.recomendar(perfil).observacoes()))
					.anyMatch(o -> o.contains("nivel"));
		}

		@Test
		void menorDe18RecebeAvisoSobreResponsavel() {
			PerfilRecomendacao perfil = comPreferencia("CHUTES");
			perfil.setIdade(15);

			assertThat(texto(service.recomendar(perfil).observacoes()))
					.anyMatch(o -> o.contains("responsavel"));
		}

		@Test
		void comRestricaoRecebeAvisoDeSaude() {
			PerfilRecomendacao perfil = comPreferencia("CHUTES");
			perfil.setPossuiRestricao(true);

			assertThat(texto(service.recomendar(perfil).observacoes()))
					.anyMatch(o -> o.contains("saude"));
		}
	}

	private List<String> texto(List<String> observacoes) {
		return observacoes.stream().map(o -> o.toLowerCase(Locale.ROOT)).toList();
	}

	private List<MartialArt> todos(Resultado resultado) {
		List<MartialArt> todos = new ArrayList<>();
		todos.add(resultado.principal());
		todos.addAll(resultado.alternativas());
		return todos;
	}

	private PerfilRecomendacao perfilValido() {
		PerfilRecomendacao perfil = new PerfilRecomendacao();
		perfil.setIdade(25);
		perfil.setAlturaCm(170);
		perfil.setPesoKg(70.0);
		return perfil;
	}

	private PerfilRecomendacao comPreferencia(String preferencia) {
		PerfilRecomendacao perfil = perfilValido();
		perfil.setPreferencia(preferencia);
		return perfil;
	}
}