package com.aise.mcnugu.dto.nugu;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class NuguOutputDto {

    private String code;
    private String message;


    @Builder
    public NuguOutputDto(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
