package com.aise.mcnugu.service;

import com.aise.mcnugu.domain.Appliance;
import com.aise.mcnugu.domain.Home;
import com.aise.mcnugu.dto.CreateAppDto;
import com.aise.mcnugu.repository.ApplianceRepository;
import com.aise.mcnugu.repository.HomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ApplianceService {

    private final ApplianceRepository applianceRepository;
    private final HomeRepository homeRepository;


    @Transactional
    public Long createAppliance(String account, CreateAppDto createAppDto) {
        Home home = homeRepository.findByCode(createAppDto.getHome_code());
        if(!home.getOwner().getId().equals(account)) {
            // 에러 발생 처리!
            System.out.println("권한이 없습니다.");
        }
        Appliance appliance = new Appliance();
        appliance.setSerialNumber(createAppDto.getSerialNumber());
        appliance.setName(createAppDto.getName());
        // 조명인지 분류는 어떻게 할거야? 시리얼넘버와 관련 있을거 같은데
        appliance.setHome(home);

        return applianceRepository.save(appliance).getId();
    }

    public List<Appliance> getAppliances(String code) {
        return applianceRepository.findAllByHome_Code(code);
    }
}
