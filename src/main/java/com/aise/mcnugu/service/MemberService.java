package com.aise.mcnugu.service;

import com.aise.mcnugu.domain.Home;
import com.aise.mcnugu.domain.Member;
import com.aise.mcnugu.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    @Transactional
    public String signup(String id, String name, String nickname, String password) {
        Member member = new Member();
        // id exist error!
        if(!memberRepository.findById(id).isEmpty()) {
            return "error";
        }
        member.setId(id);
        member.setName(name);
        member.setNickname(nickname);
        // encoding?
        member.setPassword(password);
        memberRepository.save(member);

        return member.getId();
    }

    public String login(String id, String password) {
        Member member = memberRepository.findById(id).get();
        if(password == member.getPassword()) {
            // give jwt token
            return member.getToken();
        } else {
            // pw not correct error!
            return "error";
        }
    }
}
