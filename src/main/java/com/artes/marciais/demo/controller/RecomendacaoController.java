package com.artes.marciais.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.artes.marciais.demo.model.PerfilRecomendacao;
import com.artes.marciais.demo.service.RecomendacaoService;

@Controller
public class RecomendacaoController {

	private static final List<Opcao> GENEROS = List.of(
			new Opcao("PREFIRO_NAO_INFORMAR", "Prefiro nao informar"),
			new Opcao("MULHER", "Mulher"),
			new Opcao("HOMEM", "Homem"),
			new Opcao("NAO_BINARIO", "Nao binario"));
	private static final List<Opcao> OBJETIVOS = List.of(
			new Opcao("DEFESA_PESSOAL", "Aprender defesa pessoal"),
			new Opcao("CONDICIONAMENTO", "Melhorar condicionamento fisico"),
			new Opcao("DISCIPLINA", "Desenvolver disciplina e foco"),
			new Opcao("COMPETICAO", "Preparar-me para competir"));
	private static final List<Opcao> PREFERENCIAS = List.of(
			new Opcao("SEM_PREFERENCIA", "Ainda nao tenho preferencia"),
			new Opcao("CHUTES", "Gosto de chutes e movimentacao"),
			new Opcao("GOLPES_VARIADOS", "Quero combinar punhos, chutes, joelhos e cotovelos"),
			new Opcao("ATAQUE_AGRESSIVO", "Prefiro tecnicas mais agressivas e diretas"),
			new Opcao("DEFESA_CONTROLE", "Prefiro evitar confronto, priorizando controle e imobilizacoes"),
			new Opcao("PROJECOES", "Tenho interesse em projecoes e quedas"),
			new Opcao("LUTA_NO_SOLO", "Prefiro controle e tecnicas no chao"));
	private static final List<Opcao> EXPERIENCIAS = List.of(
			new Opcao("INICIANTE", "Estou comecando agora"),
			new Opcao("INTERMEDIARIO", "Ja pratiquei por algum tempo"),
			new Opcao("AVANCADO", "Tenho experiencia avancada"));
	private static final List<Opcao> CONTATOS = List.of(
			new Opcao("LEVE", "Prefiro contato leve"),
			new Opcao("MODERADO", "Aceito contato moderado"),
			new Opcao("INTENSO", "Aceito treinos de contato intenso"));

	private final RecomendacaoService recomendacaoService;

	public RecomendacaoController(RecomendacaoService recomendacaoService) {
		this.recomendacaoService = recomendacaoService;
	}

	@GetMapping("/recomendacao")
	public String formulario(Model model) {
		model.addAttribute("perfil", new PerfilRecomendacao());
		adicionarOpcoes(model);
		return "recomendacao";
	}

	@PostMapping("/recomendacao")
	public String recomendar(@ModelAttribute("perfil") PerfilRecomendacao perfil, Model model) {
		adicionarOpcoes(model);
		Map<String, String> erros = recomendacaoService.validar(perfil);
		if (!erros.isEmpty()) {
			model.addAttribute("erros", erros);
			return "recomendacao";
		}

		model.addAttribute("resultado", recomendacaoService.recomendar(perfil));
		return "recomendacao";
	}

	private void adicionarOpcoes(Model model) {
		model.addAttribute("generos", GENEROS);
		model.addAttribute("objetivos", OBJETIVOS);
		model.addAttribute("preferencias", PREFERENCIAS);
		model.addAttribute("experiencias", EXPERIENCIAS);
		model.addAttribute("contatos", CONTATOS);
	}

	public record Opcao(String valor, String rotulo) {
	}
}
