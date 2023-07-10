package com.marvel.apiheroes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marvel.apiheroes.entity.MarvelHeroe;

@Repository
public interface MarvelHeroeRepository extends JpaRepository<MarvelHeroe, Integer> {

}
