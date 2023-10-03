package com.aise.mcnugu.repository;

import com.aise.mcnugu.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByAccount(String account);
}
