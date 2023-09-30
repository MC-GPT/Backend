package com.aise.mcnugu.repository;

import com.aise.mcnugu.domain.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}
