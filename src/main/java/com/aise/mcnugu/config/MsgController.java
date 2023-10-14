package com.aise.mcnugu.config;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MsgController {

    private final MsgService msgService;


    @PostMapping("/chatt")
    public MsgRoom createRoom(@RequestParam String name) {
        return msgService.createRoom(name);
    }

    @GetMapping("/chatt")
    public List<MsgRoom> findAllRooms() {
        return msgService.findAllRoom();
    }
}
