package com.marvel.apiheroes.response;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.marvel.apiheroes.entity.MarvelHeroe;

public class HeroesResponse {
	private final Page<MarvelHeroe> heroes;

	public HeroesResponse(final Page<MarvelHeroe> heroes) {
		this.heroes = heroes;
	}

	public Page<MarvelHeroe> getHeroes() {
		return heroes;
	}

	public ResponseEntity<HeroesResponse> toJsonResponse() {
		return new ResponseEntity<>(this, HttpStatus.OK);
	}
}
