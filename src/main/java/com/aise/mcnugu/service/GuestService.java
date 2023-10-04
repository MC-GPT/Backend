package com.aise.mcnugu.service;

import com.aise.mcnugu.domain.Guest;
import com.aise.mcnugu.domain.Home;
import com.aise.mcnugu.domain.Member;
import com.aise.mcnugu.repository.GuestRepository;
import com.aise.mcnugu.repository.HomeRepository;
import com.aise.mcnugu.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.AjcMemberMaker;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GuestService {

    private final GuestRepository guestRepository;
    private final MemberRepository memberRepository;
    private final HomeRepository homeRepository;


    public List<Home> getMyHomes(String account) {
        List<Home> myHomes = new ArrayList<>();
        Member member = memberRepository.findByAccount(account);

        for(Home h : member.getMyHomes()) {
            myHomes.add(h);
        }

        return myHomes;
    }
    public List<Home> getGuestHomes(String account) {
        List<Home> guestHomes = new ArrayList<>();
        Member member = memberRepository.findByAccount(account);

        for(Guest g : member.getHomes()) {
            guestHomes.add(g.getHome());
        }

        return guestHomes;
    }

    @Transactional
    public Long enterHome(String account, String code) {
        Member member = memberRepository.findByAccount(account);
        Home home = homeRepository.findByCode(code);

        Guest guest = new Guest();
        guest.setMember(member);
        guest.setHome(home);
        guestRepository.save(guest);

        return home.getId();
    }

}
