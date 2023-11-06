package com.aise.mcnugu.service;

import com.aise.mcnugu.domain.Home;
import com.aise.mcnugu.domain.Member;
import com.aise.mcnugu.dto.CreateHomeRequest;
import com.aise.mcnugu.dto.MainResponse;
import com.aise.mcnugu.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class HomeService {

    private final HomeRepository homeRepository;
    private final MemberRepository memberRepository;
    private final GameRepository gameRepository;
    private final GuestRepository guestRepository;
    private final ApplianceRepository applianceRepository;


    @Transactional
    public Long createHome(CreateHomeRequest createHomeRequest, String account) {
        Member owner = memberRepository.findByAccount(account);
        Home home = new Home();
        home.setName(createHomeRequest.getName());
        home.setCode(Integer.toString((int)(Math.random() * 8999) + 1000) + owner.getId());
        home.setOwner(owner);
        homeRepository.save(home);

        return home.getId();
    }

    @Transactional
    public void deleteHome(Long home_id, String account) {
        Home home = homeRepository.findById(home_id).get();
        // only owner can delete
        if(home.getOwner().getAccount().equals(account)) {
            guestRepository.deleteAllByHome_Id(home_id);
            applianceRepository.deleteAllByHome_Id(home_id);
            homeRepository.deleteById(home_id);
        }
    }

    public MainResponse mainPage(String account, Long id) {
        // 게스트인지 확인 해야지?
        Home home = homeRepository.findById(id).get();
        MainResponse mainResponse = MainResponse.builder()
                .home_id(home.getId())
                .home_name(home.getName())
                .home_code(home.getCode())
                .isOwner(account.equals(home.getOwner().getAccount()))
                .apps(home.getAppliances())
                .games(gameRepository.findAll())
                .build();
        return mainResponse;
    }

    @Transactional
    public void refreshHomeCode(String account, Long home_id) {
        Home home = homeRepository.findById(home_id).get();
        if(account.equals(home.getOwner().getAccount()))
            guestRepository.deleteAllByHome_Id(home_id);
    }
}
