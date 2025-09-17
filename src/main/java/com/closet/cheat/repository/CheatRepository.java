package com.closet.cheat.repository;


import com.closet.cheat.entity.CheatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheatRepository extends JpaRepository<CheatEntity, Long> {
    CheatEntity findByAccountOrPhone(String account, String phone);
}
