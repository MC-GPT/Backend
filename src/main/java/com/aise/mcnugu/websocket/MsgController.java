package com.aise.mcnugu.websocket;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class MsgController {

    private final MsgService msgService;


    @PostMapping("/game")
    public GameRoom createGameRoom(@RequestBody GameRoomRequest request, Principal principal) {
        return msgService.createRoom(Long.parseLong(request.getHome_id()), Long.parseLong(request.getGame_id()), principal.getName());
    }

    @Getter @Setter
    static class GameRoomRequest {
        private String home_id;
        private String game_id;
    }
}
