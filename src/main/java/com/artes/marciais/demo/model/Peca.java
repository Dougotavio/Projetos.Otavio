package com.artes.marciais.demo.model;

/**
 * Uma peca do traje usado em uma arte marcial.
 *
 * @param nome       nome da peca em portugues
 * @param descricao o que a peca e e para que serve no treino
 */
public record Peca(String nome, String descricao) {
}