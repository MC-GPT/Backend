package com.aise.mcnugu.controller;

import com.aise.mcnugu.domain.Home;
import com.aise.mcnugu.dto.LoginDto;
import com.aise.mcnugu.dto.SignupDto;
import com.aise.mcnugu.jwt.TokenInfo;
import com.aise.mcnugu.repository.GuestRepository;
import com.aise.mcnugu.service.GuestService;
import com.aise.mcnugu.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final GuestService guestService;


    @PostMapping(value = "/signup")
    public boolean signup(@RequestBody SignupDto signupDto) {
        return memberService.signup(signupDto);
    }


    @PostMapping(value = "/login")
    public TokenInfo login(@RequestBody LoginDto loginDto) {
        return memberService.login(loginDto.getAccount(), loginDto.getPassword());
    }

    @GetMapping(value = "/homes")
    public List<Home> getHomes(Principal principal) {
        // 추후 응답에서 owner, guest 분리하여 깔끔하게 응답하면 좋을듯
        List<Home> homes = new ArrayList<>();
        homes.addAll(guestService.getMyHomes(principal.getName()));
        homes.addAll(guestService.getGuestHomes(principal.getName()));
        return homes;
    }

    // 나중에 없앨거야
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
