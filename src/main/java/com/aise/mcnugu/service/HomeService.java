package com.aise.mcnugu.service;

import com.aise.mcnugu.domain.Home;
import com.aise.mcnugu.repository.HomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HomeService {

    private final HomeRepository homeRepository;


    public Long createHome(String name) {
        Home home = new Home();
        home.setName(name);
        homeRepository.save(home);

        return home.getId();
    }

    public void deleteHome(Long home_id) {
        homeRepository.deleteById(home_id);
    }
}
