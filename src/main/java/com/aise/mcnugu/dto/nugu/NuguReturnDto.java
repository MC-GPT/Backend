package com.aise.mcnugu.dto.nugu;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class NuguReturnDto {

    private String version;
    private String resultCode;
    private Object output;
    private Object action;


    @Builder
    public NuguReturnDto(String version, String resultCode, Object output, Object action) {
        this.version = version;
        this.resultCode = resultCode;
        this.output = output;
        this.action = action;
    }
}
