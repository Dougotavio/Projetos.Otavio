package com.artes.marciais.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Trata erros de forma global para que o usuario sempre receba uma pagina do
 * site, com o layout e a navegacao habituais, em vez da tela de erro padrao
 * do Spring Boot.
 */
@ControllerAdvice
public class TratadorDeErros {

	private static final Logger LOG = LoggerFactory.getLogger(TratadorDeErros.class);

	/**
	 * Parametro de URL com valor invalido, como uma disciplina que nao existe.
	 * A resposta e 404 porque o recurso pedido nao existe no catalogo.
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String parametroInvalido(Model model) {
		model.addAttribute("mensagem", "Nao encontramos essa arte marcial.");
		return "erro/404";
	}

	/** Argumento invalido chegando de fora do formulario, por exemplo na API. */
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String argumentoInvalido(Model model) {
		model.addAttribute("mensagem", "Os dados enviados nao sao validos.");
		return "erro/400";
	}

	/**
	 * Ultima linha de defesa: qualquer erro inesperado vira a pagina 500 do
 * site. A excecao original fica no log para investigacao.
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public String erroInesperado(Exception excecao, Model model) {
		LOG.error("Erro inesperado ao processar a requisicao", excecao);
		model.addAttribute("mensagem", "Algo deu errado do nosso lado. Tente novamente em instantes.");
		return "erro/500";
	}
}