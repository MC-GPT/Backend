package com.aise.mcnugu.controller;

import com.aise.mcnugu.domain.Game;
import com.aise.mcnugu.domain.Home_Game;
import com.aise.mcnugu.dto.NewGameRequest;
import com.aise.mcnugu.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;


    @PostMapping(value = "/new-game")
    public Home_Game newGame(@RequestBody NewGameRequest request, Principal principal) {
        return gameService.newGame(request, principal.getName());
    }

    @GetMapping("/enter-game")
    public Game enterGame(@RequestParam(value = "home") String home_id) {
        return gameService.enterGame(Long.parseLong(home_id));
    }
}
