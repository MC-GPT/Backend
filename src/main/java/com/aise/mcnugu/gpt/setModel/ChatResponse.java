package com.aise.mcnugu.gpt.setModel;

import lombok.*;

import java.util.List;

@AllArgsConstructor @RequiredArgsConstructor
@Getter @Setter
public class ChatResponse {

    private List<Choice> choices;


    @AllArgsConstructor @RequiredArgsConstructor
    @Getter @Setter
    public static class Choice {

        private int index;
        private Message message;
    }
}

