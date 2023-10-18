package com.aise.mcnugu.websocket;

import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class Message {
    public enum MessageType {
        ENTER, NEXT, ANSWER
    }

    private MessageType messageType;

    private String roomId;

    private String sender;

    private String Message;

    private List<URL> imageUrls = new ArrayList<>();
}
