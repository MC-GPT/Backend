package com.aise.mcnugu.gpt.setModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @RequiredArgsConstructor
@Getter @Setter
public class Message {

    private String role;
    private String content;
}
