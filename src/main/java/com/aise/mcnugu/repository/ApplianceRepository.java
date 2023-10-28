package com.aise.mcnugu.repository;

import com.aise.mcnugu.domain.Appliance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplianceRepository extends JpaRepository<Appliance, Long> {

    public List<Appliance> findAllByHomeId(Long home_id);

    public void deleteAllByHome_Id(Long home_id);
}
