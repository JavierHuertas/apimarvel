package com.marvel.apiheroes.request;

public class HeroRequest {
	private String nombre;
	private String descripcion;
	private String urlImagen;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(final String urlImagen) {
		this.urlImagen = urlImagen;
	}
}
