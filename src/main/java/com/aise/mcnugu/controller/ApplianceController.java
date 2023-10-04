package com.aise.mcnugu.controller;

import com.aise.mcnugu.domain.Appliance;
import com.aise.mcnugu.dto.CreateAppDto;
import com.aise.mcnugu.service.ApplianceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ApplianceController {

    private final ApplianceService applianceService;


    @PostMapping(value = "/create-app")
    public Long createAppliance(Principal principal, @RequestBody CreateAppDto createAppDto) {
        return applianceService.createAppliance(principal.getName(), createAppDto);
    }

    @GetMapping(value = "/apps")
    public List<Appliance> getAppliances(@RequestParam(value = "code") String home_code) {
        // 주인 또는 게스트인지 확인하는 코드 필요하겠다!
        return applianceService.getAppliances(home_code);
    }
}
