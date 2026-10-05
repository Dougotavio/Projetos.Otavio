package com.artes.marciais.demo.controller;

import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.artes.marciais.demo.model.Disciplina;
import com.artes.marciais.demo.model.MartialArt;
import com.artes.marciais.demo.service.MartialArtService;

/**
 * Controla as paginas do site. Todas as rotas resolvem para templates
 * Thymeleaf (src/main/resources/templates).
 */
@Controller
public class MartialArtController {

	private final MartialArtService service;

	public MartialArtController(MartialArtService service) {
		this.service = service;
	}

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("artes", service.listarTodas());
		model.addAttribute("total", service.totalDeArtes());
		model.addAttribute("maisAntiga", service.maisAntiga());
		model.addAttribute("maisRecente", service.maisRecente());
		return "home";
	}

	@GetMapping("/artes")
	public String lista(Model model) {
		model.addAttribute("artes", service.listarPorOrdemCronologica());
		return "lista";
	}

	@GetMapping("/comparativo")
	public String comparativo(Model model) {
		model.addAttribute("artes", service.listarTodas());
		model.addAttribute("disciplinas", service.disciplinas());
		return "comparativo";
	}

	@GetMapping("/sobre")
	public String sobre(Model model) {
		model.addAttribute("total", service.totalDeArtes());
		return "sobre";
	}

	@GetMapping("/lojas")
	public String lojas() {
		return "lojas";
	}

	@GetMapping("/artes/disciplina/{disciplina}")
	public String porDisciplina(@PathVariable String disciplina, Model model, HttpServletResponse response) {
		Optional<Disciplina> escolhida = DisciplineParser.buscar(disciplina);
		if (escolhida.isEmpty()) {
			return naoEncontrado(response);
		}
		model.addAttribute("artes", service.listarPorDisciplina(escolhida.get()));
		model.addAttribute("disciplina", escolhida.get());
		return "lista";
	}

	@GetMapping("/artes/{slug}")
	public String detalhe(@PathVariable String slug, Model model, HttpServletResponse response) {
		return service.buscarPorSlug(slug)
				.map(arte -> {
					model.addAttribute("arte", arte);
					model.addAttribute("outras", service.outras(arte));
					return "detalhe";
				})
				.orElseGet(() -> naoEncontrado(response));
	}

	@GetMapping("/artes/{slug}/treino")
	public String treino(@PathVariable String slug, Model model, HttpServletResponse response) {
		return service.buscarPorSlug(slug)
				.map(arte -> {
					model.addAttribute("arte", arte);
					return "treino";
				})
				.orElseGet(() -> naoEncontrado(response));
	}

	/**
	 * Renderiza a pagina de erro com status HTTP 404, para que um slug
	 * inexistente nao seja respondido como 200 OK.
	 */
	private String naoEncontrado(HttpServletResponse response) {
		response.setStatus(HttpStatus.NOT_FOUND.value());
		return "erro/404";
	}
}
