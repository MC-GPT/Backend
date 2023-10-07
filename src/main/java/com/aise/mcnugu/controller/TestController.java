package com.aise.mcnugu.controller;

import com.aise.mcnugu.gpt.ChatService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// 나중에 없앨 controller
@RestController
@RequiredArgsConstructor
public class TestController {

    private final ChatService chatService;


    @PostMapping("/gpt")
    public JSONObject gptTest(@RequestBody String question) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(chatService.getChatResponse(question));
        JSONObject jsonObj = (JSONObject) obj;
        return jsonObj;
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
