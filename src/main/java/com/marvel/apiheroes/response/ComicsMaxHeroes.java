package com.marvel.apiheroes.response;

import java.util.List;

public class ComicsMaxHeroes {

	private final Integer comicId;
	private final String comicNombre;
	private final int cantidadPersonajes;
	private final List<heroeXComicsResponse> personajes;

	public ComicsMaxHeroes(final Integer comicId, final String comicNombre, final int cantidadPersonajes,
			final List<heroeXComicsResponse> personajes) {
		this.comicId = comicId;
		this.comicNombre = comicNombre;
		this.cantidadPersonajes = cantidadPersonajes;
		this.personajes = personajes;
	}

	public Integer getComicId() {
		return comicId;
	}

	public String getComicNombre() {
		return comicNombre;
	}

	public int getCantidadPersonajes() {
		return cantidadPersonajes;
	}

	public List<heroeXComicsResponse> getPersonajes() {
		return personajes;
	}
}