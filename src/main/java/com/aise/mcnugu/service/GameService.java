package com.aise.mcnugu.service;

import com.aise.mcnugu.domain.Game;
import com.aise.mcnugu.domain.Home_Game;
import com.aise.mcnugu.domain.Member;
import com.aise.mcnugu.dto.NewGameRequest;
import com.aise.mcnugu.repository.GameRepository;
import com.aise.mcnugu.repository.HomeRepository;
import com.aise.mcnugu.repository.Home_GameRepository;
import com.aise.mcnugu.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GameService {

    private final GameRepository gameRepository;
    private final Home_GameRepository home_gameRepository;
    private final HomeRepository homeRepository;
    private final MemberRepository memberRepository;


    @Transactional
    public Game newGame(NewGameRequest request, String account) {
        // 게임이 이미 존재하는 경우에는..?
        Home_Game home_game = new Home_Game();
        home_game.setHome(homeRepository.findById(request.getHome_id()).get());
        home_game.setGame(gameRepository.findById(request.getGame_id()).get());
        home_game.setMc(memberRepository.findByAccount(account));
        home_gameRepository.save(home_game);
        return gameRepository.findById(request.getGame_id()).get();
    }

    public Game enterGame(Long home_id) {
        return home_gameRepository.findAllByHomeId(home_id).get().getGame();
    }

    @Transactional
    public Long register(Long home_id, String account) {
        Home_Game home_game = home_gameRepository.findAllByHomeId(home_id).get();
        Member member = memberRepository.findByAccount(account);
        home_game.getOrder().add(member);
        home_gameRepository.save(home_game); // transactional 처리 있어서 안해되 되려나?
        return home_id;
    }

    public List<String> getRegister(Long home_id) {
        List<String> result = new ArrayList<>();
        List<Member> members = home_gameRepository.findAllByHomeId(home_id).get().getOrder();
        for(Member m : members) {
            result.add(m.getNickname());
        }
        return result;
    }

    @Transactional
    public void refreshGame(Long home_id) {
        home_gameRepository.findAllByHomeId(home_id).get().refresh();
    }
}
