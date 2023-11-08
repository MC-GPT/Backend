package com.aise.mcnugu.dto.nugu;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class NuguGatherUpDto {

    // 시작 인원
    private int game_num;


    @Builder
    public NuguGatherUpDto(int game_num) {
        this.game_num = game_num;
    }
}
