package com.mongol.animewar.repository;

import com.mongol.animewar.model.Villain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VillainRepository extends JpaRepository<Villain, Integer> {
}
