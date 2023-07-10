package com.marvel.apiheroes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.marvel.apiheroes.entity.MarvelHeroe;
import com.marvel.apiheroes.repository.MarvelHeroeRepository;

@Service
public class MarvelHeroeService {

	@Autowired
	MarvelHeroeRepository heroeRepo;

	public MarvelHeroe getHeroById(final Integer id) {

		return heroeRepo.findById(id).get();
	}

	public void saveHero(final MarvelHeroe hero) {

		heroeRepo.save(hero);
	}

	public void deleteHeroById(final Integer id) {
		heroeRepo.deleteById(id);
	}

	public Page<MarvelHeroe> getAllHeroes(final Pageable pageable) {

		return heroeRepo.findAll(pageable);
	}

}
