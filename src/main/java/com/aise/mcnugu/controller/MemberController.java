package com.aise.mcnugu.controller;

import com.aise.mcnugu.domain.dto.SignRequest;
import com.aise.mcnugu.jwt.TokenInfo;
import com.aise.mcnugu.service.MemberService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @PostMapping(value = "/signup")
    public boolean signup(@RequestBody SignRequest request) {
        //request.setPassword(passwordEncoder.encode(request.getPassword()));
        return memberService.signup(request);
    }


    @PostMapping(value = "/login")
    public TokenInfo login(@RequestBody LoginDto memberLoginRequestDto) {
        String account = memberLoginRequestDto.getAccount();
        String password = memberLoginRequestDto.getPassword();
        TokenInfo tokenInfo = memberService.login(account, password);

        return tokenInfo;
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }


    @Getter @Setter
    static class LoginDto {
        String account;
        String password;
    }
}
