package com.aise.mcnugu.websocket;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter
public class GameRoom {

    private Long roomId;
    private Set<WebSocketSession> sessions = new HashSet<>();  // 현재 연결중인 클라이언트 정보


    @Builder
    public GameRoom(Long roomId) {
        this.roomId = roomId;
    }

    public void handleActions(WebSocketSession session, Message message, MsgService msgService) {
        if (message.getMessageType().equals(Message.MessageType.ENTER)) {
            sessions.add(session);
            message.setMessage(message.getSender() + "입장");
        }
        sendMessage(message, msgService);
    }

    public <T> void sendMessage(T message, MsgService msgService) {
        sessions.parallelStream().forEach(session -> msgService.sendMessage(session, message));
    }
}
