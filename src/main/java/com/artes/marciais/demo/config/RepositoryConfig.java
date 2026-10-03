package com.artes.marciais.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.artes.marciais.demo.repository.MartialArtRepository;

/**
 * Registra a implementacao do repositorio como bean.
 *
 * Trocar a origem dos dados significa apenas apontar para outra implementacao
 * de {@link MartialArtRepository}; o controller continua igual.
 */
@Configuration
public class RepositoryConfig {

	@Bean
	MartialArtRepository martialArtRepository() {
		return new MartialArtRepository.InMemory();
	}
}
