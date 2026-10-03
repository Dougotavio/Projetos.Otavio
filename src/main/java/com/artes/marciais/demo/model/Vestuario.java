package com.artes.marciais.demo.model;

import java.util.List;

/**
 * Registro imutavel do traje de uma arte marcial: como a roupa e chamada,
 * quais pecas formam o conjunto, como as cores e os niveis sao usados e quais
 * cuidados o praticante precisa ter com o material.
 *
 * @param slugArte   arte do catalogo a qual o traje pertence
 * @param nomeRoupa  nome do uniforme em portugues
 * @param nomeNativo nome no idioma ou na tradicao de origem
 * @param resumo     frase curta usada no cabecalho do bloco
 * @param tecido     material e gramatura usuais
 * @param cores      regras de cor do traje
 * @param pecas      pecas que formam o conjunto
 * @param niveis     faixas, cores ou progressao usada pela arte
 * @param calcado    regra de calcado usada no tatame
 * @param cuidados  cuidados de uso, lavagem e guardado
 * @param imagemUrl URL da imagem ilustrativa do traje
 * @param fonteImagemUrl pagina de origem e licenca da imagem
 * @param creditoImagem credito da imagem conforme a licenca
 * @param termoBusca termo seguro para montar buscas nos sites de venda
 */
public record Vestuario(
		String slugArte,
		String nomeRoupa,
		String nomeNativo,
		String resumo,
		String tecido,
		List<String> cores,
		List<Peca> pecas,
		List<String> niveis,
		String calcado,
		List<Destaque> cuidados,
		String imagemUrl,
		String fonteImagemUrl,
		String creditoImagem,
		String termoBusca) {
}