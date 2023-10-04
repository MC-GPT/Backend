package com.aise.mcnugu.controller;

import com.aise.mcnugu.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class GuestController {

    private final GuestService guestService;


    @PostMapping("/enter-home")
    public Long enterHome(Principal principal, String code) {
        return guestService.enterHome(principal.getName(), code);
    }
}
