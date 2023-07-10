package com.marvel.apiheroes.response;

public class heroeXComicsResponse {

	private final Integer personajeId;
	private final String personajeNombre;

	public heroeXComicsResponse(final Integer personajeId, final String personajeNombre) {
		this.personajeId = personajeId;
		this.personajeNombre = personajeNombre;
	}

	public Integer getPersonajeId() {
		return personajeId;
	}

	public String getPersonajeNombre() {
		return personajeNombre;
	}

}
