package com.aise.mcnugu.controller;

import com.aise.mcnugu.domain.Home;
import com.aise.mcnugu.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class GuestController {

    private final GuestService guestService;


    @PostMapping("/enter-home")
    public Home enterHome(Principal principal, @RequestBody Map<String, String> home_code_map) {
        System.out.println(principal.getName());
        System.out.println(home_code_map);
        return guestService.enterHome(principal.getName(), home_code_map.get("home_code"));
    }
}
