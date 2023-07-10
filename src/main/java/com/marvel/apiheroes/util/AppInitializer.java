package com.marvel.apiheroes.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marvel.apiheroes.entity.MarvelComic;
import com.marvel.apiheroes.entity.MarvelHeroe;
import com.marvel.apiheroes.repository.MarvelComicRepository;
import com.marvel.apiheroes.repository.MarvelHeroeRepository;

@Component
public class AppInitializer implements CommandLineRunner {

	@Value("${datos.hash}")
	private String hash;
	@Value("${datos.publickey}")
	private String publicKey;

	private static final Logger logger = LoggerFactory.getLogger(AppInitializer.class);

	@Autowired
	MarvelHeroeRepository heroeRepository;
	@Autowired
	MarvelComicRepository comicrepository;

	public void guardarBaseDeDatos(final List<MarvelHeroe> heroes) {
		for (final MarvelHeroe heroe : heroes) {
			final Set<MarvelComic> comicsXHeroe = heroe.getComics();
			final MarvelHeroe saved = heroeRepository.save(heroe);

			for (final MarvelComic comic : comicsXHeroe) {
				final MarvelComic comiccopia = comicrepository.findById(comic.getId()).orElse(null);
				if (comiccopia == null) {
					comicrepository.save(comic);
				} else {
					comiccopia.getHeroes().add(saved);
				}
			}
		}
	}

	@Override
	@Transactional
	public void run(final String... args) throws Exception {

		final int limit = 100;
		int total = 300;

		final List<MarvelHeroe> heroes = new ArrayList<MarvelHeroe>();

		ResponseEntity<String> responseHeroes;
		MarvelHeroe heroe = null;

		final RestTemplate restTemplate = new RestTemplate();

		for (int offset = 0; offset <= total; offset += limit) {

			logger.info("datos {}", offset);
			logger.info("total {}", total);
			logger.info("quedan {}", total - offset);
			logger.info("nos saltamos {}", offset);
			final String urlCharacters = "https://gateway.marvel.com:443/v1/public/characters?apikey=" + publicKey
					+ "&ts=1&hash=" + hash + "&offset=" + offset + "&limit=" + 100;
			responseHeroes = restTemplate.exchange(urlCharacters, HttpMethod.GET, null, String.class);
			if (responseHeroes.getStatusCode().is2xxSuccessful()) {
				final String responseBody = responseHeroes.getBody();
				final ObjectMapper objectMapper = new ObjectMapper();

				final JsonNode rootNode = objectMapper.readTree(responseBody);
				final JsonNode resultsNode = rootNode.get("data").get("results");

				total = rootNode.get("data").get("total").asInt();

				for (final JsonNode node : resultsNode) {
					final String nombre = node.get("name").asText();
					final String description = node.get("description").asText();
					final String uriImagen = node.get("thumbnail").get("path").asText();
					heroe = new MarvelHeroe(nombre, description, uriImagen);

					final JsonNode comicsNode = node.get("comics").get("items");

					// Cómics en los que sale el personaje actual
					for (final JsonNode nodec : comicsNode) {
						final String name = nodec.get("name").asText();
						final String url = nodec.get("resourceURI").asText();
						final int lastIndex = url.lastIndexOf("/");
						final String result = url.substring(lastIndex + 1);
						final MarvelComic comic = new MarvelComic(Integer.valueOf(result), name);

						heroe.getComics().add(comic);

					}

					heroes.add(heroe);

				}

			} else {
				logger.error("La llamada a la API externa no fue exitosa. Código de estado: {}",
						responseHeroes.getStatusCode());
			}
		}
		guardarBaseDeDatos(heroes);

	}
}
