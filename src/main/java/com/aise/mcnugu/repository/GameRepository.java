package com.aise.mcnugu.repository;

import com.aise.mcnugu.domain.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
