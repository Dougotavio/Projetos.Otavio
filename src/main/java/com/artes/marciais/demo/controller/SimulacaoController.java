package com.artes.marciais.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.artes.marciais.demo.model.AtributosLuta;
import com.artes.marciais.demo.service.MartialArtService;
import com.artes.marciais.demo.service.SimulacaoService;
import com.artes.marciais.demo.service.SimulacaoService.Resultado;

/**
 * Pagina de simulacao de duelo entre duas artes marciais.
 *
 * O formulario usa dois seletores e a mesma linguagem visual das demais
 * paginas: hero, blocos de cartao e avisos em rodape.
 */
@Controller
public class SimulacaoController {

	private final SimulacaoService simulacaoService;
	private final MartialArtService martialArtService;

	public SimulacaoController(SimulacaoService simulacaoService,
			MartialArtService martialArtService) {
		this.simulacaoService = simulacaoService;
		this.martialArtService = martialArtService;
	}

	@GetMapping("/simulacao")
	public String formulario(Model model) {
		preparar(model);
		return "simulacao";
	}

	@PostMapping("/simulacao")
	public String simular(@RequestParam String primeira, @RequestParam String segunda,
			Model model) {
		preparar(model);
		model.addAttribute("selecionadaPrimeira", primeira);
		model.addAttribute("selecionadaSegunda", segunda);

		try {
			Resultado resultado = simulacaoService.simular(primeira, segunda);
			model.addAttribute("resultado", resultado);
		} catch (SimulacaoService.DueloInvalidoException | IllegalArgumentException erro) {
			model.addAttribute("erroSimulacao", erro.getMessage());
		}
		return "simulacao";
	}

	private void preparar(Model model) {
		List<AtributosLuta> atributos = simulacaoService.listarAtributos();
		model.addAttribute("artes", martialArtService.listarTodas());
		model.addAttribute("atributos", atributos);
		model.addAttribute("distancias", simulacaoService.distanciasPadrao());
		if (model.getAttribute("selecionadaPrimeira") == null && !atributos.isEmpty()) {
			model.addAttribute("selecionadaPrimeira", atributos.get(0).slug());
			model.addAttribute("selecionadaSegunda", atributos.get(1).slug());
		}
	}
}