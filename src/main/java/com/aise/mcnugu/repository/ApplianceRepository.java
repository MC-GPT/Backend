package com.aise.mcnugu.repository;

import com.aise.mcnugu.domain.Appliance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplianceRepository extends JpaRepository<Appliance, Long> {
}
