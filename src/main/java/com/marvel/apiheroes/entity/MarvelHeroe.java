package com.marvel.apiheroes.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "marvel_heroe")
public class MarvelHeroe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "descripcion", length = 7000)
	private String descripcion;

	@Column(name = "url_imagen")
	private String urlImagen;

	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "heroe_comic", joinColumns = @JoinColumn(name = "heroe_id"), inverseJoinColumns = @JoinColumn(name = "comic_id"))
	private Set<MarvelComic> comics = new HashSet<>();

	// Constructor vacío
	public MarvelHeroe() {
	}

	// Constructor con parámetros
	public MarvelHeroe(final String nombre, final String descripcion, final String urlImagen) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.urlImagen = urlImagen;

	}

	// Getters y setters

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

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

	public Set<MarvelComic> getComics() {
		return comics;
	}

	public void setComics(final Set<MarvelComic> comics) {
		this.comics = comics;
	}
}
