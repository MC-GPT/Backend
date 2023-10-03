package com.aise.mcnugu.controller;

import com.aise.mcnugu.dto.LoginDto;
import com.aise.mcnugu.dto.SignupDto;
import com.aise.mcnugu.jwt.TokenInfo;
import com.aise.mcnugu.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @PostMapping(value = "/signup")
    public boolean signup(@RequestBody SignupDto signupDto) {
        return memberService.signup(signupDto);
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
}
