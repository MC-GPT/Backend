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


    @PostMapping(value = "/gather-up")
    public NuguReturnDto conn(@RequestBody String nugu) {
        NuguReturnDto nuguReturnDto = NuguReturnDto.builder()
                .version("2.0")
                .resultCode("OK")
                .build();
        return nuguReturnDto;
    }


    // 시작 인원 적으면 종료
    @PostMapping(value = "/gather-up/check")
    public NuguReturnDto gatherup(@RequestBody String nugu) {
        JSONObject jsonObject = new JSONObject(nugu);
        String version = jsonObject.getString("version");
        JSONObject action = jsonObject.getJSONObject("action");
        JSONObject parameters = action.getJSONObject("parameters");
        JSONObject j_start_num = parameters.getJSONObject("start_num");

        int start = j_start_num.getInt("value");
        if(start <= 2) {
            NuguOutputDto nuguOutputDto = NuguOutputDto.builder()
                    .code("start_error")
                    .message("start number가 너무 작습니다.")
                    .build();

            NuguReturnDto nuguReturnDto = NuguReturnDto.builder()
                    .version(version)
                    .resultCode("ERROR")
                    .output(nuguOutputDto)
                    .build();
            return nuguReturnDto;
        } else {
            NuguReturnDto nuguReturnDto = NuguReturnDto.builder()
                    .version(version)
                    .resultCode("OK")
                    .build();
            return nuguReturnDto;
        }
    }

    // 음악 재생
    @PostMapping(value = "/gather-up/resumeWhileStopped")
    public NuguReturnDto resumeWhileStopped(@RequestBody String nugu) {
        JSONObject jsonObject = new JSONObject(nugu);
        String version = jsonObject.getString("version");
        JSONObject action = jsonObject.getJSONObject("action");
        JSONObject parameters = action.getJSONObject("parameters");

        NuguAudioDto.AudioItem.Stream stream = NuguAudioDto.AudioItem.Stream.builder()
                .url("https://www.naver.com/")
                .offsetInMilliseconds(0)
                .build();

        NuguAudioDto.AudioItem audioItem = NuguAudioDto.AudioItem.builder()
                .stream(stream)
                .build();

        NuguAudioDto nuguAudioDto = NuguAudioDto.builder()
                .type("AudioPlayer.Play")
                .audioItem(audioItem)
                .build();

        NuguActionDto nuguActionDto = NuguActionDto.builder()
                .type("SetValue")
                .key("timer")
                .value("stop")
                .delayInMilliseconds(10000)
                .build();

        NuguReturnDto nuguReturnDto = NuguReturnDto.builder()
                .version(version)
                .resultCode("OK")
                .directives(nuguAudioDto)
                .action(nuguActionDto)
                .build();

        return nuguReturnDto;
    }

    // 게임 모여라 명수 반환
    @PostMapping(value = "/gather-up/answer")
    public NuguReturnDto answer(@RequestBody String nugu) {
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
