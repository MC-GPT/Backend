package com.aise.mcnugu.service;

import com.aise.mcnugu.domain.Home;
import com.aise.mcnugu.domain.Member;
import com.aise.mcnugu.dto.CreateHomeRequest;
import com.aise.mcnugu.dto.MainResponse;
import com.aise.mcnugu.repository.GameRepository;
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
    private final GameRepository gameRepository;


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

    public MainResponse mainPage(String account, Long id) {
        // 게스트인지 확인 해야지?
        Home home = homeRepository.findById(id).get();
        MainResponse mainResponse = MainResponse.builder()
                .home_name(home.getName())
                .isOwner(account.equals(home.getOwner().getAccount()))
                .apps(home.getAppliances())
                .games(gameRepository.findAll())
                .build();
        return mainResponse;
    }

    @Transactional
    public String refreshHomeCode(String account, Long home_id) {
        Home home = homeRepository.findById(home_id).get();
        if(account.equals(home.getOwner().getAccount()))
            return home.refreshCode();
        else
            // 권한이 없을 경우 에러!
            return "error!";
    }
}
