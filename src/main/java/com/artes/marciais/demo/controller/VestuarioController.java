package com.artes.marciais.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.artes.marciais.demo.service.MartialArtService;
import com.artes.marciais.demo.service.VestuarioService;

/**
 * Pagina unica que mostra o traje de cada arte marcial. Os blocos do template
 * reaproveitam o tema visual de origem de cada modalidade, o mesmo usado nas
 * paginas de detalhe.
 */
@Controller
public class VestuarioController {

	private final MartialArtService martialArtService;
	private final VestuarioService vestuarioService;

	public VestuarioController(MartialArtService martialArtService, VestuarioService vestuarioService) {
		this.martialArtService = martialArtService;
		this.vestuarioService = vestuarioService;
	}

	@GetMapping("/roupas")
	public String roupas(Model model) {
		model.addAttribute("artes", martialArtService.listarTodas());
		model.addAttribute("vestuarioPorArte", vestuarioService.porArte());
		return "roupas";
	}
}