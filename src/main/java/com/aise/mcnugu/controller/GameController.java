package com.aise.mcnugu.controller;

import com.aise.mcnugu.domain.Game;
import com.aise.mcnugu.dto.NewGameRequest;
import com.aise.mcnugu.service.GameService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;


    @PostMapping(value = "/new-game")
    public Game newGame(@RequestBody NewGameRequest request, Principal principal) {
        return gameService.newGame(request, principal.getName());
    }

    @GetMapping("/enter-game")
    public Game enterGame(@RequestParam(value = "home") String home_id) {
        return gameService.enterGame(Long.parseLong(home_id));
    }

    @PostMapping("/order-register")
    public void register(@RequestBody HomeRequest request, Principal principal) {
        gameService.register(Long.parseLong(request.getHome_id()), principal.getName());
    }

    @GetMapping("/order-register")
    public List<String> getRegister(@RequestParam(value = "home") String home_id) {
        return gameService.getRegister(Long.parseLong(home_id));
    }

    @PostMapping("/refresh-game")
    public void refreshGame(@RequestBody HomeRequest request) {
        gameService.refreshGame(Long.parseLong(request.getHome_id()));
    }


    @Getter @Setter
    static class HomeRequest {
        String home_id;
    }
}
