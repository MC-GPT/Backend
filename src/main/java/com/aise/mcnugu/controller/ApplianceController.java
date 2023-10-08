package com.aise.mcnugu.controller;

import com.aise.mcnugu.domain.Appliance;
import com.aise.mcnugu.dto.CreateAppRequest;
import com.aise.mcnugu.service.ApplianceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class ApplianceController {

    private final ApplianceService applianceService;


    @PostMapping(value = "/create-app")
    public Long createAppliance(Principal principal, @RequestBody CreateAppRequest request) {
        return applianceService.createAppliance(principal.getName(), request);
    }

    @GetMapping(value = "/apps")
    public Appliance getApp(@RequestParam(value = "id") String app_id) {
        return applianceService.getApp(Long.parseLong(app_id));
    }
}
