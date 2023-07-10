package com.marvel.apiheroes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldApplication {

	@GetMapping("/")
	public String hello() {
		return "{\"mensaje\": \"Hola\"}";
	}
}
