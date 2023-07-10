package com.marvel.apiheroes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.marvel.apiheroes.entity.MarvelComic;

@Repository
public interface MarvelComicRepository extends JpaRepository<MarvelComic, Integer> {

	@Query("SELECT c.id, c.name, COUNT(h.id) AS cantidad_personajes " + "FROM MarvelComic c JOIN c.heroes h "
			+ "GROUP BY c.id, c.name " + "ORDER BY COUNT(h.id) DESC " + "LIMIT :n")
	List<Object[]> findComicsWithMostCharacters(@Param("n") int n);
}
