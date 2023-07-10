package com.marvel.apiheroes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marvel.apiheroes.entity.MarvelHeroe;
import com.marvel.apiheroes.repository.MarvelComicRepository;
import com.marvel.apiheroes.response.ComicsMaxHeroes;
import com.marvel.apiheroes.response.heroeXComicsResponse;

@Service
public class MarvelComicService {

	@Autowired
	private MarvelComicRepository comicRepository;

	public List<ComicsMaxHeroes> obtenerComicsConMasPersonajes(final int cantidad) {
		final List<ComicsMaxHeroes> devolver = new ArrayList<ComicsMaxHeroes>();

		final List<Object[]> ajustar = comicRepository.findComicsWithMostCharacters(cantidad);
		for (final Object[] comic : ajustar) {
			final Integer comicId = (Integer) comic[0];
			final String comicNombre = (String) comic[1];
			final long cantidadPersonajes = (long) comic[2];
			final List<heroeXComicsResponse> heroes = new ArrayList<heroeXComicsResponse>();

			for (final MarvelHeroe heroe : comicRepository.findById(comicId).get().getHeroes()) {
				heroes.add(new heroeXComicsResponse(heroe.getId(), heroe.getNombre()));
			}
			final ComicsMaxHeroes rellenar = new ComicsMaxHeroes(comicId, comicNombre, (int) cantidadPersonajes,
					heroes);
			devolver.add(rellenar);
		}

		return devolver;
	}

}
