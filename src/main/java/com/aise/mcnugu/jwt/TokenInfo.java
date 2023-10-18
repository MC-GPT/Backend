package com.aise.mcnugu.jwt;

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

    // account, nickname 정보도 추가로 response
}
