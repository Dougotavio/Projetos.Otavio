package com.artes.marciais.demo.repository;

import java.util.List;

import com.artes.marciais.demo.model.Academia;

/**
 * Catalogo de exemplo para o mapa de academias.
 *
 * IMPORTANTE: estes registros sao FICTICIOS. Os nomes, enderecos e telefones
 * nao existem e servem apenas para demonstrar o funcionamento do mapa.
 *
 * As coordenadas sao de bairros reais da Grande Sao Paulo para que o mapa
 * mostre uma distribuicao crivel. Para publicar dados verdadeiros, troque os
 * registros abaixo por academias reais, confirme nome, endereco e telefone com
 * a propria academia e mude o campo {@code exemplo} para {@code false}.
 */
final class AcademiaCatalogo {

	private AcademiaCatalogo() {
	}

	static List<Academia> academias() {
		return List.of(
				exemplo("dojo-bela-vista", "Dojo Bela Vista", "Bela Vista, Sao Paulo",
						"Exemplo nao real", -23.5613, -46.6565,
						List.of("judo", "karate"), "Telefone de exemplo"),
				exemplo("tatami-moca", "Tatami Mooca", "Mooca, Sao Paulo",
						"Exemplo nao real", -23.5985, -46.6311,
						List.of("jiu-jitsu", "judo"), "Telefone de exemplo"),
				exemplo("academia-pinheiros", "Academia Pinheiros", "Pinheiros, Sao Paulo",
						"Exemplo nao real", -23.5654, -46.6815,
						List.of("muay-thai", "boxe"), "Telefone de exemplo"),
				exemplo("centro-karate-tatuape", "Centro de Karate Tatuape", "Tatuape, Sao Paulo",
						"Exemplo nao real", -23.5403, -46.5761,
						List.of("karate", "taekwondo"), "Telefone de exemplo"),
				exemplo("jiu-jitsu-vila-madalena", "Jiu-Jitsu Vila Madalena", "Vila Madalena, Sao Paulo",
						"Exemplo nao real", -23.5469, -46.6915,
						List.of("jiu-jitsu"), "Telefone de exemplo"),
				exemplo("striking-butanta", "Striking Butanta", "Butanta, Sao Paulo",
						"Exemplo nao real", -23.5713, -46.7309,
						List.of("boxe", "muay-thai", "taekwondo"), "Telefone de exemplo"),
				exemplo("dojo-santo-amaro", "Dojo Santo Amaro", "Santo Amaro, Sao Paulo",
						"Exemplo nao real", -23.6534, -46.6774,
						List.of("judo", "karate", "jiu-jitsu"), "Telefone de exemplo"),
				exemplo("academia-santo-andre", "Academia Santo Andre", "Santo Andre",
						"Exemplo nao real", -23.6775, -46.4556,
						List.of("muay-thai", "boxe"), "Telefone de exemplo"),
				exemplo("dojo-sao-bernardo", "Dojo Sao Bernardo", "Sao Bernardo do Campo",
						"Exemplo nao real", -23.6914, -46.5525,
						List.of("judo", "jiu-jitsu"), "Telefone de exemplo"),
				exemplo("academia-osasco", "Academia Osasco", "Osasco",
						"Exemplo nao real", -23.5558, -46.7956,
						List.of("taekwondo", "karate"), "Telefone de exemplo"),
				exemplo("ct-guarulhos", "Centro de Treino Guarulhos", "Guarulhos",
						"Exemplo nao real", -23.4628, -46.6822,
						List.of("boxe", "muay-thai"), "Telefone de exemplo"),
				exemplo("dojo-campinas", "Dojo Campinas", "Campinas",
						"Exemplo nao real", -22.9068, -47.1747,
						List.of("judo", "jiu-jitsu", "karate"), "Telefone de exemplo"));
	}

	private static Academia exemplo(String slug, String nome, String bairro, String endereco,
			double latitude, double longitude, List<String> artes, String telefone) {
		return new Academia(slug, nome, bairro, endereco, latitude, longitude,
				artes, null, telefone, true);
	}
}