package com.aise.mcnugu.controller;

import com.aise.mcnugu.dto.CreateHomeDto;
import com.aise.mcnugu.dto.MainResponse;
import com.aise.mcnugu.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;


    @PostMapping(value = "/create-home")
    public Long createHome(Principal principal, @RequestBody CreateHomeDto createHomeDto) {
        return homeService.createHome(createHomeDto, principal.getName());
    }

    @GetMapping("/main")
    public MainResponse mainPage(Principal principal, @RequestParam(value = "home") String home_id) {
        return homeService.mainPage(principal.getName(), Long.parseLong(home_id));
    }
}
