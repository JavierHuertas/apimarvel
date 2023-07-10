package com.marvel.apiheroes.response;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.marvel.apiheroes.entity.MarvelComic;
import com.marvel.apiheroes.entity.MarvelHeroe;

public class HeroResponse {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private final MarvelHeroe hero;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private final Set<MarvelComic> comics;

	public HeroResponse(final MarvelHeroe hero, final Set<MarvelComic> comics) {
		this.hero = hero;
		this.comics = comics;
	}

	public MarvelHeroe getHero() {
		return hero;
	}

	public Set<MarvelComic> getComics() {
		return comics;
	}

	public ResponseEntity<HeroResponse> toJsonResponse() {
		return new ResponseEntity<>(this, HttpStatus.OK);
	}
}
