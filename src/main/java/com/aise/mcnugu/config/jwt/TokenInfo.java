package com.aise.mcnugu.config.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class TokenInfo {

    private String account;

    private String nickname;

    private String grantType;

    private String accessToken;

    private String refreshToken;
}
