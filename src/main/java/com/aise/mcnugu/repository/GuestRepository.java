package com.aise.mcnugu.repository;

import com.aise.mcnugu.domain.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GuestRepository extends JpaRepository<Guest, Long> {

    public void deleteAllByHome_Id(Long home_id);
}
