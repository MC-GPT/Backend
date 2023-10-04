package com.aise.mcnugu.service;

import com.aise.mcnugu.domain.Home;
import com.aise.mcnugu.domain.Member;
import com.aise.mcnugu.dto.CreateHomeDto;
import com.aise.mcnugu.repository.HomeRepository;
import com.aise.mcnugu.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HomeService {

    private final HomeRepository homeRepository;
    private final MemberRepository memberRepository;


    @Transactional
    public Long createHome(CreateHomeDto createHomeDto, String account) {
        Member owner = memberRepository.findByAccount(account);
        Home home = new Home();
        home.setName(createHomeDto.getName());
        home.setCode(Integer.toString((int)(Math.random() * 8999) + 1000) + owner.getId());
        home.setOwner(owner);
        homeRepository.save(home);

        return home.getId();
    }

    @Transactional
    public void deleteHome(Long home_id) {
        homeRepository.deleteById(home_id);
    }
}
