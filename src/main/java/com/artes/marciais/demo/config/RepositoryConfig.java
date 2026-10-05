package com.artes.marciais.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.artes.marciais.demo.repository.AcademiaRepository;
import com.artes.marciais.demo.repository.AtributosLutaRepository;
import com.artes.marciais.demo.repository.MartialArtRepository;
import com.artes.marciais.demo.repository.VestuarioRepository;

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

	@Bean
	AtributosLutaRepository atributosLutaRepository() {
		return new AtributosLutaRepository.InMemory();
	}

	@Bean
	AcademiaRepository academiaRepository() {
		return new AcademiaRepository.InMemory();
	}

	@Bean
	VestuarioRepository vestuarioRepository() {
		return new VestuarioRepository.InMemory();
	}
}
