package com.aise.mcnugu.service;

import com.aise.mcnugu.domain.Appliance;
import com.aise.mcnugu.domain.Home;
import com.aise.mcnugu.dto.CreateAppRequest;
import com.aise.mcnugu.repository.ApplianceRepository;
import com.aise.mcnugu.repository.HomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplianceService {

    private final ApplianceRepository applianceRepository;
    private final HomeRepository homeRepository;


    @Transactional
    public Long createAppliance(String account, CreateAppRequest request) {
        Home home = homeRepository.findById(request.getHome_id()).get();
        if(!account.equals(home.getOwner().getAccount())) {
            // 권한 없음
            return Long.valueOf(0);
        }
        Appliance appliance = new Appliance();
        appliance.setSerialNumber(request.getSerialNumber());
        appliance.setName(request.getName());
        appliance.setLight(false);
        appliance.setType(0);
        appliance.setLocked(false);
        appliance.setHome(home);

        return applianceRepository.save(appliance).getId();
    }
}
