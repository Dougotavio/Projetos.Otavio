package com.artes.marciais.demo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.ui.ExtendedModelMap;

import com.artes.marciais.demo.controller.MartialArtController;
import com.artes.marciais.demo.controller.VestuarioController;
import com.artes.marciais.demo.model.Disciplina;
import com.artes.marciais.demo.model.MartialArt;
import com.artes.marciais.demo.model.PerfilRecomendacao;
import com.artes.marciais.demo.model.Vestuario;
import com.artes.marciais.demo.service.MartialArtService;
import com.artes.marciais.demo.service.RecomendacaoService;
import com.artes.marciais.demo.service.RecomendacaoService.Resultado;
import com.artes.marciais.demo.service.VestuarioService;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private MartialArtService service;

	@Autowired
	private RecomendacaoService recomendacaoService;

	@Autowired
	private VestuarioService vestuarioService;

	@Test
	void contextLoads() {
	}

	@Test
	void catalogoTemSeisArtesIncluindoBoxe() {
		assertThat(service.listarTodas()).hasSize(6);
		assertThat(service.buscarPorSlug("boxe")).isPresent()
				.get()
				.satisfies(boxe -> {
					assertThat(boxe.nome()).isEqualTo("Boxe");
					assertThat(boxe.disciplina()).isEqualTo(Disciplina.STRIKING);
					assertThat(boxe.historia()).contains("Rocky Balboa");
				});
	}

	@Test
	void todaArteTemHistoriaEAnaliseCompleta() {
		for (MartialArt arte : service.listarTodas()) {
			assertThat(arte.historia()).isNotBlank();
			assertThat(arte.resumo()).isNotBlank();
			assertThat(arte.pontosFortes()).hasSizeGreaterThanOrEqualTo(3);
			assertThat(arte.pontosFracos()).hasSizeGreaterThanOrEqualTo(3);
			assertThat(arte.videoId()).isNotBlank();
			assertThat(arte.icone()).isNotBlank();
		}
	}

	@Test
	void slugsSaoUnicos() {
		assertThat(service.listarTodas())
				.extracting(MartialArt::slug)
				.doesNotHaveDuplicates();
	}

	@Test
	void buscaPorSlugFunciona() {
		assertThat(service.buscarPorSlug("judo")).isPresent();
		assertThat(service.buscarPorSlug("inexistente")).isEmpty();
	}

	@Test
	void paginasDeTreinoEstaoDisponiveisParaTodasAsArtes() {
		MartialArtController controller = new MartialArtController(service);
		List<String> slugs = List.of("judo", "karate", "taekwondo", "jiu-jitsu", "muay-thai", "boxe");

		for (String slug : slugs) {
			ExtendedModelMap model = new ExtendedModelMap();

			assertThat(controller.treino(slug, model, new MockHttpServletResponse())).isEqualTo("treino");
			assertThat(model.get("arte")).isEqualTo(service.buscarPorSlug(slug).orElseThrow());
		}

		ExtendedModelMap modelInvalido = new ExtendedModelMap();
		MockHttpServletResponse response = new MockHttpServletResponse();
		assertThat(controller.treino("inexistente", modelInvalido, response)).isEqualTo("erro/404");
		assertThat(response.getStatus()).isEqualTo(404);
		assertThat(modelInvalido).doesNotContainKey("arte");
	}

	@Test
	void slugInexistenteRespondeComStatus404() {
		MartialArtController controller = new MartialArtController(service);
		MockHttpServletResponse response = new MockHttpServletResponse();

		assertThat(controller.detalhe("inexistente", new ExtendedModelMap(), response))
				.isEqualTo("erro/404");
		assertThat(response.getStatus()).isEqualTo(404);
	}

	@Test
	void paginaDeRoupasCobreTodasAsArtes() {
		VestuarioController controller = new VestuarioController(service, vestuarioService);
		ExtendedModelMap model = new ExtendedModelMap();

		assertThat(controller.roupas(model)).isEqualTo("roupas");
		assertThat(model.get("artes")).isEqualTo(service.listarTodas());

		@SuppressWarnings("unchecked")
		Map<String, Vestuario> porArte = (Map<String, Vestuario>) model.get("vestuarioPorArte");

		assertThat(porArte).hasSize(service.listarTodas().size());
		for (MartialArt arte : service.listarTodas()) {
			assertThat(porArte).containsKey(arte.slug());
		}
	}

	@Test
	void cadaTrajeTemPecasCoresECuidados() {
		for (Vestuario vestuario : vestuarioService.listarTodas()) {
			assertThat(vestuario.nomeRoupa()).isNotBlank();
			assertThat(vestuario.nomeNativo()).isNotBlank();
			assertThat(vestuario.resumo()).isNotBlank();
			assertThat(vestuario.tecido()).isNotBlank();
			assertThat(vestuario.calcado()).isNotBlank();
			assertThat(vestuario.cores()).hasSizeGreaterThanOrEqualTo(2);
			assertThat(vestuario.pecas()).hasSizeGreaterThanOrEqualTo(4);
			assertThat(vestuario.niveis()).hasSizeGreaterThanOrEqualTo(3);
			assertThat(vestuario.cuidados()).hasSizeGreaterThanOrEqualTo(3);
			assertThat(vestuario.imagemUrl()).startsWith("https://");
			assertThat(vestuario.fonteImagemUrl()).startsWith("https://");
			assertThat(vestuario.creditoImagem()).isNotBlank();
			assertThat(vestuario.termoBusca()).isNotBlank();
		}
	}

	@Test
	void slugsDeTrajeCorrespondemAoCatalogoDeArtes() {
		assertThat(vestuarioService.listarTodas())
				.extracting(Vestuario::slugArte)
				.containsExactlyInAnyOrderElementsOf(
						service.listarTodas().stream().map(MartialArt::slug).toList());
	}

	@Test
	void trajeTemNomeConhecidoDaArte() {
		assertThat(vestuarioService.buscarPorArte("judo").orElseThrow().nomeRoupa())
				.isEqualTo("Judogi");
		assertThat(vestuarioService.buscarPorArte("karate").orElseThrow().nomeRoupa())
				.isEqualTo("Karategi");
		assertThat(vestuarioService.buscarPorArte("taekwondo").orElseThrow().nomeRoupa())
				.isEqualTo("Dobok");
		assertThat(vestuarioService.buscarPorArte("boxe").orElseThrow().nomeRoupa())
				.isEqualTo("Ring attire");
		assertThat(vestuarioService.buscarPorArte("inexistente")).isEmpty();
	}

	@Test
	void ordenacaoCronologicaEstaCorreta() {
		List<MartialArt> artes = service.listarPorOrdemCronologica();
		for (int i = 1; i < artes.size(); i++) {
			assertThat(artes.get(i - 1).anoOrigem()).isLessThanOrEqualTo(artes.get(i).anoOrigem());
		}
		assertThat(service.buscarPorSlug("boxe").orElseThrow().anoOrigem()).isEqualTo(1867);
	}

	@Test
	void filtroPorDisciplinaFunciona() {
		List<MartialArt> resultado = service.listarPorDisciplina(Disciplina.GRAPPLING);
		assertThat(resultado).isNotEmpty();
		assertThat(resultado).allMatch(arte -> arte.disciplina() == Disciplina.GRAPPLING);
	}

	@Test
	void recomendacaoAcompanhaPreferenciaTecnica() {
		assertThat(recomendar("CHUTES").principal().slug()).isEqualTo("taekwondo");
		assertThat(recomendar("GOLPES_VARIADOS").principal().slug()).isEqualTo("muay-thai");
		assertThat(recomendar("ATAQUE_AGRESSIVO").principal().slug()).isEqualTo("muay-thai");
		assertThat(recomendar("DEFESA_CONTROLE").principal().slug()).isEqualTo("jiu-jitsu");
		assertThat(recomendar("PROJECOES").principal().slug()).isEqualTo("judo");
		assertThat(recomendar("LUTA_NO_SOLO").principal().slug()).isEqualTo("jiu-jitsu");
	}

	@Test
	void recomendacaoIncluiBoxeNasAlternativasParaCompeticao() {
		PerfilRecomendacao perfil = perfilValido();
		perfil.setObjetivo("COMPETICAO");
		Resultado resultado = recomendacaoService.recomendar(perfil);

		assertThat(java.util.stream.Stream.concat(
				java.util.stream.Stream.of(resultado.principal()),
				resultado.alternativas().stream())
				.map(MartialArt::slug)
				.toList())
				.contains("boxe");
	}

	@Test
	void generoNaoAlteraARecomendacao() {
		PerfilRecomendacao perfil = perfilValido();
		perfil.setPreferencia("CHUTES");
		String resultadoSemInformar = recomendacaoService.recomendar(perfil).principal().slug();

		perfil.setGenero("MULHER");
		assertThat(recomendacaoService.recomendar(perfil).principal().slug())
				.isEqualTo(resultadoSemInformar);
	}

	@Test
	void recomendacaoValidaFaixasDosDadosInformados() {
		PerfilRecomendacao perfil = perfilValido();
		perfil.setIdade(5);
		perfil.setAlturaCm(250);
		perfil.setPesoKg(0.0);

		assertThat(recomendacaoService.validar(perfil))
				.containsKeys("idade", "alturaCm", "pesoKg");
	}

	private Resultado recomendar(String preferencia) {
		PerfilRecomendacao perfil = perfilValido();
		perfil.setPreferencia(preferencia);
		return recomendacaoService.recomendar(perfil);
	}

	private PerfilRecomendacao perfilValido() {
		PerfilRecomendacao perfil = new PerfilRecomendacao();
		perfil.setIdade(25);
		perfil.setAlturaCm(170);
		perfil.setPesoKg(70.0);
		return perfil;
	}
}
