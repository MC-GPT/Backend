package com.aise.mcnugu.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class SignRequest {
    private String account;
    private String name;
    private String nickname;
    private String password;
}
