package com.aise.mcnugu.controller;

import com.aise.mcnugu.dto.CreateHomeRequest;
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
    public Long createHome(Principal principal, @RequestBody CreateHomeRequest createHomeRequest) {
        return homeService.createHome(createHomeRequest, principal.getName());
    }

    @GetMapping("/main")
    public MainResponse mainPage(Principal principal, @RequestParam(value = "home") String home_id) {
        return homeService.mainPage(principal.getName(), Long.parseLong(home_id));
    }

    @PostMapping(value = "/refresh")
    public String refreshHomeCode(Principal principal, @RequestParam(value = "home") String home_id) {
        return homeService.refreshHomeCode(principal.getName(), Long.parseLong(home_id));
    }
}
