package com.aise.mcnugu.repository;

import com.aise.mcnugu.domain.Home;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeRepository extends JpaRepository<Home, Long> {

    public Home findByCode(String code);
}
