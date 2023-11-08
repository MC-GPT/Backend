package com.aise.mcnugu.dto.nugu;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class NuguGatherUpDto {

    private int start_num;
    private int game_num;


    @Builder
    public NuguGatherUpDto(int start_num, int game_num) {
        this.start_num = start_num;
        this.game_num = game_num;
    }
}
