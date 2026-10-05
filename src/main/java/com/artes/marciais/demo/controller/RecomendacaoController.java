package com.artes.marciais.demo.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.artes.marciais.demo.model.Contato;
import com.artes.marciais.demo.model.Experiencia;
import com.artes.marciais.demo.model.Genero;
import com.artes.marciais.demo.model.Objetivo;
import com.artes.marciais.demo.model.PerfilRecomendacao;
import com.artes.marciais.demo.model.Preferencia;
import com.artes.marciais.demo.service.RecomendacaoService;

@Controller
public class RecomendacaoController {

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

	/**
	 * Opcoes do formulario derivadas dos enums, para que rotulo e valor nao
	 * fiquem duplicados em relacao as regras de pontuacao do service.
	 */
	private void adicionarOpcoes(Model model) {
		model.addAttribute("generos", opcoes(Genero.values(), Genero::getRotulo));
		model.addAttribute("objetivos", opcoes(Objetivo.values(), Objetivo::getRotulo));
		model.addAttribute("preferencias", opcoes(Preferencia.values(), Preferencia::getRotulo));
		model.addAttribute("experiencias", opcoes(Experiencia.values(), Experiencia::getRotulo));
		model.addAttribute("contatos", opcoes(Contato.values(), Contato::getRotulo));
	}

	/**
	 * Monta as opcoes do formulario a partir de um enum, usando o nome da
	 * constante como valor enviado e {@code rotulo} como texto exibido.
	 */
	private static <T extends Enum<T>> List<Opcao> opcoes(T[] valores, Function<T, String> rotulo) {
		return Arrays.stream(valores)
				.map(valor -> new Opcao(valor.name(), rotulo.apply(valor)))
				.toList();
	}

	public record Opcao(String valor, String rotulo) {
	}
}
