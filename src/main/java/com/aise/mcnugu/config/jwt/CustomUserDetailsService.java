package com.aise.mcnugu.config.jwt;

import com.aise.mcnugu.domain.Member;
import com.aise.mcnugu.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        Member member = memberRepository.findByAccount(account);
        UserDetails userDetails = User.builder()
                .username(member.getAccount())
                .password(member.getPassword())
                .roles("USER")
                .build();
        return userDetails;
//        return memberRepository.findByAccount(account)
//                .map(this::createUserDetails)
//                .orElseThrow(() -> new UsernameNotFoundException("해당하는 유저를 찾을 수 없습니다."));
    }

    // 해당하는 User 의 데이터가 존재한다면 UserDetails 객체로 만들어서 리턴
//    private UserDetails createUserDetails(Member member) {
//        return User.builder()
//                .username(member.getAccount())
//                .password(member.getPassword())
//                .roles("USER")
//                .build();
//    }
}
