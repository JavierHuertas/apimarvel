package com.marvel.apiheroes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marvel.apiheroes.response.ComicsMaxHeroes;
import com.marvel.apiheroes.service.MarvelComicService;

@RestController
@RequestMapping("/comics")
public class ComicController {

	@Autowired
	MarvelComicService comicService;

	@GetMapping("/conMasHeroes/{n}")
	public List<ComicsMaxHeroes> showEditForm(@PathVariable("n") final Integer n) {

		return comicService.obtenerComicsConMasPersonajes(n);
	}

}
