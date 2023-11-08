package com.aise.mcnugu.dto.nugu;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class NuguErrorDto {

    private String code;
    private String message;


    @Builder
    public NuguErrorDto(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
