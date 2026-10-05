package com.artes.marciais.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.artes.marciais.demo.model.Academia;
import com.artes.marciais.demo.model.MartialArt;
import com.artes.marciais.demo.service.AcademiaService;
import com.artes.marciais.demo.service.MartialArtService;

/**
 * Mapa das academias da Grande Sao Paulo, filtravel por arte e por cidade.
 *
 * Os registros atuais sao de exemplo; o proprio template avisa o usuario.
 */
@Controller
public class AcademiaController {

	private final AcademiaService academiaService;
	private final MartialArtService martialArtService;

	public AcademiaController(AcademiaService academiaService,
			MartialArtService martialArtService) {
		this.academiaService = academiaService;
		this.martialArtService = martialArtService;
	}

	@GetMapping("/academias")
	public String mapa(@RequestParam(required = false) List<String> arte,
			@RequestParam(required = false) String cidade, Model model) {
		List<String> artesSelecionadas = arte == null ? List.of() : arte.stream()
				.filter(Objects::nonNull)
				.filter(s -> !s.isBlank())
				.toList();

		List<Academia> academias = academiaService.listarPorArtes(artesSelecionadas).stream()
				.filter(a -> cidade == null || cidade.isBlank()
						|| "todas".equalsIgnoreCase(cidade)
						|| cidadeDe(a).equalsIgnoreCase(cidade))
				.toList();

		model.addAttribute("academias", academias);
		model.addAttribute("total", academias.size());
		model.addAttribute("totalGeral", academiaService.listarTodas().size());
		model.addAttribute("artes", artesDisponiveis(artesSelecionadas));
		model.addAttribute("cidades", academiaService.cidades());
		model.addAttribute("cidadeSelecionada", cidade == null ? "todas" : cidade);
		model.addAttribute("artesSelecionadas", artesSelecionadas);
		model.addAttribute("nomeDasArtes", nomeDasArtes());
		return "academias";
	}

	private String cidadeDe(Academia academia) {
		String bairro = academia.bairro();
		return bairro.contains(",") ? bairro.split(", ")[1] : bairro;
	}

	/** Opcoes de arte, marcando as ja selecionadas. */
	private List<Object[]> artesDisponiveis(List<String> selecionadas) {
		return martialArtService.listarTodas().stream()
				.map(arte -> new Object[] { arte.slug(), arte.nome(),
						selecionadas.contains(arte.slug()) })
				.toList();
	}

	/** Mapa slug -> nome, usado pelo mapa em javascript. */
	private Map<String, String> nomeDasArtes() {
		return martialArtService.listarTodas().stream()
				.collect(Collectors.toMap(MartialArt::slug, MartialArt::nome));
	}
}