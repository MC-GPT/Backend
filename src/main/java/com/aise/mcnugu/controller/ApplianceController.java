package com.aise.mcnugu.controller;

import com.aise.mcnugu.domain.Appliance;
import com.aise.mcnugu.dto.CreateAppRequest;
import com.aise.mcnugu.service.ApplianceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ApplianceController {

    private final ApplianceService applianceService;


    @PostMapping(value = "/create-app")
    public Long createAppliance(Principal principal, @RequestBody CreateAppRequest request) {
        return applianceService.createAppliance(principal.getName(), request);
    }

    @GetMapping(value = "/apps")
    public List<Appliance> getApps(@RequestParam(value = "home") String home_id) {
        return applianceService.getApps(Long.parseLong(home_id));
    }

    @GetMapping(value = "/app")
    public Appliance getApp(@RequestParam(value = "id") String app_id) {
        return applianceService.getApp(Long.parseLong(app_id));
    }
}
