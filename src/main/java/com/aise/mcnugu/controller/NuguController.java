package com.aise.mcnugu.controller;

import com.aise.mcnugu.dto.nugu.*;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@Slf4j
public class NuguController {

    Random random = new Random();

    // 게임 모여라 명수 반환
    @PostMapping(value = "/answer")
    public NuguReturnDto answer(@RequestBody String nugu) {
        log.info("answer");
        log.info(nugu);
        JSONObject jsonObject = new JSONObject(nugu);
        String version = jsonObject.getString("version");
        JSONObject action = jsonObject.getJSONObject("action");
        JSONObject parameters = action.getJSONObject("parameters");
        JSONObject j_start_num = parameters.getJSONObject("start_num");

        int start = j_start_num.getInt("value");
        int game_num = 0;

        if (start % 2 == 0) {
            int[] numbers = {3, 5};
            game_num = numbers[random.nextInt(numbers.length)];
        } else {
            int[] numbers = {2, 4};
            game_num = numbers[random.nextInt(numbers.length)];
        }

        NuguGatherUpDto nuguGatherUpDto = NuguGatherUpDto.builder()
                .game_num(game_num)
                .build();

        NuguReturnDto nuguReturnDto = NuguReturnDto.builder()
                .version(version)
                .resultCode("OK")
                .output(nuguGatherUpDto)
                .build();

        return nuguReturnDto;
    }
}
