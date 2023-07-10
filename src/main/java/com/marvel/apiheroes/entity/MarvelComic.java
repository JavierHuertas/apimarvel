package com.marvel.apiheroes.entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "marvel_comic")
public class MarvelComic {

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "nombre")
	private String name;

	@JsonIgnore
	@ManyToMany(mappedBy = "comics")
	private Set<MarvelHeroe> heroes = new HashSet<>();

	// Constructor vacío
	public MarvelComic() {
	}

	// Constructor con parámetros
	public MarvelComic(final Integer id, final String name) {
		this.id = id;
		this.name = name;
	}

	// Getters y setters

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Set<MarvelHeroe> getHeroes() {
		return heroes;
	}

	public void setHeroes(final Set<MarvelHeroe> heroes) {
		this.heroes = heroes;
	}

	// Otros getters y setters
}