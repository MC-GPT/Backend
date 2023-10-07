package com.aise.mcnugu.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignupRequest {

    private String account;

    private String name;

    private String nickname;

    private String password;
}
