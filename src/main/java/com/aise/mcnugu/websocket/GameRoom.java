package com.aise.mcnugu.websocket;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter @Setter
public class GameRoom {

    private Long roomId;
    private Set<WebSocketSession> sessions = new HashSet<>();  // 현재 연결중인 클라이언트 정보

    // host session 정보로, 진행자에게만 정답만 전송 and 끊길 시, 게임 종료
    private WebSocketSession host;

    // 정답자 신청 리스트
    private List<String> serial = new ArrayList<>();


    @Builder
    public GameRoom(Long roomId) {
        this.roomId = roomId;
    }

    public void refreshSerial() {
        this.serial = new ArrayList<>();
    }


    public void handleActions(WebSocketSession session, Message message, MsgService msgService) {
        if (message.getMessageType().equals(Message.MessageType.ENTER)) {
            if (host == null)
                host = session;
            sessions.add(session);
            message.setMessage(message.getSender() + " 입장");
            // 추후 삭제
            sendMessage(message.getSender(), msgService);
        }
        if (message.getMessageType().equals(Message.MessageType.NEXT)) {
            // 다음 문제 (Flask -> 프론트)에서 받아서 채팅에 쏴줘~
            sendMessage(message.getImageUrl(), msgService);
        }
        if (message.getMessageType().equals(Message.MessageType.ANSWER)) {
            // 문제 정답 (Flask -> 프론트)에서 받아서 채팅에 쏴줘~
            sendMessage(message.getImageUrl(), msgService);
        }
    }

    public <T> void sendMessage(T message, MsgService msgService) {
        sessions.parallelStream().forEach(session -> msgService.sendMessage(session, message));
    }
}
