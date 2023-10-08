package com.aise.mcnugu.repository;

import com.aise.mcnugu.domain.Home_Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Home_GameRepository extends JpaRepository<Home_Game, Long> {

    public Optional<Home_Game> findAllByHomeId(Long home_id);
}
