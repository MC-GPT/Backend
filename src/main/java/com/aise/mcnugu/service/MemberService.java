package com.aise.mcnugu.service;

import com.aise.mcnugu.domain.Authority;
import com.aise.mcnugu.domain.Guest;
import com.aise.mcnugu.domain.Home;
import com.aise.mcnugu.domain.Member;
import com.aise.mcnugu.dto.SignupDto;
import com.aise.mcnugu.jwt.JwtTokenProvider;
import com.aise.mcnugu.jwt.TokenInfo;
import com.aise.mcnugu.repository.GuestRepository;
import com.aise.mcnugu.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final GuestRepository guestRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;


    @Transactional
    public boolean signup(SignupDto request) {
        Member member = new Member();
        member.setAccount(request.getAccount());
        member.setName(request.getName());
        member.setNickname(request.getNickname());
        member.setPassword(passwordEncoder.encode(request.getPassword()));
        member.setRoles(Collections.singletonList(Authority.builder().name("USER").build()));

        memberRepository.save(member);
        return true;
    }

    public TokenInfo login(String account, String password) {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(account, password);

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        return tokenInfo;
    }
}
