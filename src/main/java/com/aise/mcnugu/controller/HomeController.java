package com.aise.mcnugu.controller;

import com.aise.mcnugu.domain.Home;
import com.aise.mcnugu.dto.CreateHomeDto;
import com.aise.mcnugu.repository.HomeRepository;
import com.aise.mcnugu.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;
    private final HomeRepository homeRepository;


    @PostMapping(value = "/create-home")
    public Long createHome(Principal principal, @RequestBody CreateHomeDto createHomeDto) {
        return homeService.createHome(createHomeDto, principal.getName());
    }

    @GetMapping("/main")
    public Home mainRoom(@RequestParam(value = "code") String code) {
        // 주인 또는 게스트인지 확인하는 코드 필요하겠다!
        return homeRepository.findByCode(code);
    }
}
