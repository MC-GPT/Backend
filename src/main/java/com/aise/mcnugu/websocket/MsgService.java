package com.aise.mcnugu.websocket;

import com.aise.mcnugu.domain.Home_Game;
import com.aise.mcnugu.repository.GameRepository;
import com.aise.mcnugu.repository.HomeRepository;
import com.aise.mcnugu.repository.Home_GameRepository;
import com.aise.mcnugu.repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class MsgService {

    private final ObjectMapper objectMapper;
    private Map<Long, GameRoom> gameRooms;

    private final Home_GameRepository home_gameRepository;
    private final HomeRepository homeRepository;
    private final GameRepository gameRepository;
    private final MemberRepository memberRepository;


    @PostConstruct
    private void init() {
        // home_game repository 에서 다 가져와야하나..
        gameRooms = new LinkedHashMap<>();
    }

    public GameRoom findById(Long roomId) {
        return gameRooms.get(roomId);
    }

    public GameRoom createRoom(Long home_id, Long game_id, String account) {
        Home_Game home_game = new Home_Game();
        home_game.setHome(homeRepository.findById(home_id).get());
        home_game.setGame(gameRepository.findById(game_id).get());
        home_game.setMc(memberRepository.findByAccount(account));
        home_gameRepository.save(home_game);

        GameRoom gameRoom = GameRoom.builder().roomId(home_game.getId()).build();

        gameRooms.put(home_game.getId(), gameRoom);
        return gameRoom;
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
