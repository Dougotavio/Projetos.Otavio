package com.artes.marciais.demo.model;

import java.util.List;

/**
 * Registro de uma academia, com a(localizacao e as artes que ela ensina.
 *
 * @param slug       identificador da academia
 * @param nome       nome da academia
 * @param bairro     bairro ou cidade na Grande Sao Paulo
 * @param endereco   endereco completo
 * @param latitude   latitude em graus, usada para posicionar o marcador
 * @param longitude  longitude em graus, usada para posicionar o marcador
 * @param artes      slugs das artes marciais ensinadas, do catalogo principal
 * @param site       site ou pagina de contato, quando houver
 * @param telefone   telefone para contato, quando houver
 * @param exemplo    marca que o registro faz parte do catalogo de exemplo
 *                   e nao representa uma academia real
 */
public record Academia(
		String slug,
		String nome,
		String bairro,
		String endereco,
		double latitude,
		double longitude,
		List<String> artes,
		String site,
		String telefone,
		boolean exemplo) {

	public boolean ensina(String slugArte) {
		return artes.contains(slugArte);
	}

	/** Link para abrir a localizacao no Google Maps. */
	public String googleMapsUrl() {
		return "https://www.google.com/maps/search/?api=1&query=" + latitude + "," + longitude;
	}
}