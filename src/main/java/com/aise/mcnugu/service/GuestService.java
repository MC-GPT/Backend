package com.aise.mcnugu.service;

import com.aise.mcnugu.domain.Guest;
import com.aise.mcnugu.domain.Home;
import com.aise.mcnugu.domain.Member;
import com.aise.mcnugu.repository.GuestRepository;
import com.aise.mcnugu.repository.HomeRepository;
import com.aise.mcnugu.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;
    private final MemberRepository memberRepository;
    private final HomeRepository homeRepository;


    public Long registerHome(Long member_id, String home_code) {
        Member member = memberRepository.findById(member_id).get();
        Home home = homeRepository.findByCode(home_code);
        Guest guest = new Guest();
        guest.setMember(member);
        guest.setHome(home);
        guestRepository.save(guest);

        return guest.getId();
    }

    public List<Home> findHomes(Long member_id) {
        List<Home> homes = new ArrayList<>();
        homes.addAll(memberRepository.findById(member_id).get().getMyHomes());
        for(Guest g : memberRepository.findById(member_id).get().getHomes()) {
            homes.add(g.getHome());
        }

        return homes;
    }
}
