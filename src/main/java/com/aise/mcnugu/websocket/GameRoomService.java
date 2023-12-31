package com.aise.mcnugu.websocket;

import com.aise.mcnugu.domain.Game;
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

// 임시 게임방일 뿐이기에, DB 저장 안할거야
@Service
@RequiredArgsConstructor
@Slf4j
public class GameRoomService {

    private final ObjectMapper objectMapper;
    private Map<Long, GameRoom> gameRooms;

    private final Home_GameRepository home_gameRepository;
    private final HomeRepository homeRepository;
    private final GameRepository gameRepository;
    private final MemberRepository memberRepository;


    @PostConstruct
    private void init() {
        gameRooms = new LinkedHashMap<>();
    }

    public GameRoom findById(Long roomId) {
        return gameRooms.get(roomId);
    }

    public Long getGameType(Long roomId) {
        Home_Game home_game = home_gameRepository.findById(roomId).get();
        System.out.println(home_game);
        Game game = home_game.getGame();
        System.out.println(game);
        return game.getGameType();
    }

    // host 의 session 이 닫히면, 삭제
    public void deleteBySession(WebSocketSession session) {
        List<Long> roomsToRemove = new ArrayList<>();

        for (Map.Entry<Long, GameRoom> entry : gameRooms.entrySet()) {
            Long id = entry.getKey();
            GameRoom gameRoom = entry.getValue();

            if (gameRoom != null && gameRoom.getHost() != null && gameRoom.getHost().equals(session)) {
                // Add rooms to remove to a list to avoid concurrent modification
                roomsToRemove.add(id);
                home_gameRepository.deleteById(id);
                // 삭제 메시지 -> guest 모두 방 퇴출! (프론트에서 구현)
            }
        }

        // Now, remove the game rooms after the loop to avoid concurrent modification
        for (Long idToRemove : roomsToRemove) {
            gameRooms.remove(idToRemove);
        }
    }


    public Long createRoom(Long home_id, Long game_id, String account) {
        Home_Game home_game = new Home_Game();
        home_game.setHome(homeRepository.findById(home_id).get());
        home_game.setGame(gameRepository.findById(game_id).get());
        home_game.setMc(memberRepository.findByAccount(account));
        home_gameRepository.save(home_game);

        GameRoom gameRoom = GameRoom.builder().roomId(home_game.getId()).build();
        gameRooms.put(home_game.getId(), gameRoom);
        return gameRoom.getRoomId();
    }

    public Long enterRoom(Long home_id) {
        Home_Game home_game = home_gameRepository.findAllByHomeId(home_id).get();
        GameRoom gameRoom = gameRooms.get(home_game.getId());
        if (gameRoom.getHost() == null) {
            return null;
        }
        return gameRooms.get(home_game.getId()).getRoomId();
    }

    public boolean registerSerial(Long roomId, String account) {
        GameRoom gameRoom = gameRooms.get(roomId);
        gameRoom.getSerial().add(memberRepository.findByAccount(account).getNickname());
        gameRooms.replace(roomId, gameRoom);
        return true;
    }

    public String getNextSerial(Long roomId) {
        GameRoom gameRoom = gameRooms.get(roomId);
        String nickname = gameRoom.getSerial().get(0);
        gameRoom.getSerial().remove(0);
        return nickname;
    }

    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
