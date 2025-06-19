package com.mongol.animewar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mongol.animewar.model.Characters;

public interface CharactersRepository extends JpaRepository<Characters, Integer> {
}
