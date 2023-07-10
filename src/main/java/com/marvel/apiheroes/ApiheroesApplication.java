package com.marvel.apiheroes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.marvel.apiheroes.repository")
@EntityScan("com.marvel.apiheroes.entity")
@SpringBootApplication
public class ApiheroesApplication {

	public static void main(final String[] args) {
		SpringApplication.run(ApiheroesApplication.class, args);
	}

}
