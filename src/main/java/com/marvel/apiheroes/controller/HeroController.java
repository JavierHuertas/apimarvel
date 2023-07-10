package com.marvel.apiheroes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marvel.apiheroes.entity.MarvelHeroe;
import com.marvel.apiheroes.request.HeroRequest;
import com.marvel.apiheroes.response.HeroResponse;
import com.marvel.apiheroes.response.HeroesResponse;
import com.marvel.apiheroes.service.MarvelHeroeService;

@RestController
@RequestMapping("/heroes")
public class HeroController {

	@Autowired
	private MarvelHeroeService heroService;

	@GetMapping
	public ResponseEntity<HeroesResponse> getHeroes(final Pageable pageable) {
		final Page<MarvelHeroe> heroPage = heroService.getAllHeroes(pageable);
		final HeroesResponse response = new HeroesResponse(heroPage);
		return response.toJsonResponse();
	}

	@PostMapping("/{id}/edit")
	public String saveEditedHero(@PathVariable("id") final Integer id,
			@ModelAttribute("hero") final MarvelHeroe editedHero) {
		final MarvelHeroe hero = heroService.getHeroById(id);

		hero.setNombre(editedHero.getNombre());
		hero.setDescripcion(editedHero.getDescripcion());
		hero.setUrlImagen(editedHero.getUrlImagen());

		heroService.saveHero(hero); // Save the updated hero

		return "personaje editado correctamente" + id;
	}

	@GetMapping("/{id}/delete")
	public String deleteHero(@PathVariable("id") final Integer id) {
		heroService.deleteHeroById(id);
		return "Heroe con id:" + id + " Ha sido borrado";
	}

	@GetMapping("/comics/{id}")
	public HeroResponse comicsHeroe(@PathVariable("id") final Integer id) {
		final MarvelHeroe hero = heroService.getHeroById(id);

		final HeroResponse heroesComics = new HeroResponse(hero, hero.getComics());

		return heroesComics;
	}

	@PostMapping("/create")
	public String createHero(@RequestBody final HeroRequest heroRequest) {

		final String nombre = heroRequest.getNombre();
		final String descripcion = heroRequest.getDescripcion();
		final String urlImagen = heroRequest.getUrlImagen();
		final MarvelHeroe hero = new MarvelHeroe(nombre, descripcion, urlImagen);
		heroService.saveHero(hero);
		return "Heroe creado";
	}

}