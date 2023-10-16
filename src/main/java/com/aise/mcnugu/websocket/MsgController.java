package com.aise.mcnugu.websocket;

import com.aise.mcnugu.dto.GameRoomRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class MsgController {

    private final MsgService msgService;


    @PostMapping("/new-game")
    public Long createGameRoom(@RequestBody GameRoomRequest request, Principal principal) {
        // 방 생성하고, 프론트에서 ENTER 요청
        return msgService.createRoom(Long.parseLong(request.getHome_id()), Long.parseLong(request.getGame_id()), principal.getName());
    }

    @GetMapping("/enter-game")
    public Long enterGameRoom(@RequestParam(value = "home") String home_id) {
        // 방 입장하고, 프론트에서 ENTER 요청
        // Flask 게임 url 보내줘야해!
        return msgService.enterRoom(Long.parseLong(home_id));
    }

    @PostMapping("/register-serial")
    public boolean registerSerial(@RequestParam("roomId") String roomId, Principal principal) {
        return msgService.registerSerial(Long.parseLong(roomId), principal.getName());
    }

    @PostMapping("/get-serial")
    public String getSerial(@RequestParam("roomId") String roomId) {
        return msgService.getNextSerial(Long.parseLong(roomId));
    }
}
