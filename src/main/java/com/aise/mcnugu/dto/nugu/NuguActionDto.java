package com.aise.mcnugu.dto.nugu;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class NuguActionDto {

    private String type;
    private String key;
    private String value;
    private int delayInMilliseconds;


    @Builder
    public NuguActionDto(String type, String key, String value, int delayInMilliseconds) {
        this.type = type;
        this.key = key;
        this.value = value;
        this.delayInMilliseconds = delayInMilliseconds;
    }
}
