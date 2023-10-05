package com.aise.mcnugu.repository;

import com.aise.mcnugu.domain.Home;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HomeRepository extends JpaRepository<Home, Long> {

    public Optional<Home> findById(Long id);
    public Home findByCode(String code);
}
