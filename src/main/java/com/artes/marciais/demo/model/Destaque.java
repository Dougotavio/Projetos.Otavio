package com.artes.marciais.demo.model;

/**
 * Um item de analise de uma arte marcial: uma vantagem ou uma desvantagem.
 *
 * @param titulo    resumo curto do item
 * @param descricao explicacao em uma frase
 */
public record Destaque(String titulo, String descricao) {
}
