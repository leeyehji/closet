package com.closet.cheat.repository;


import com.closet.cheat.entity.CheatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CheatRepository extends JpaRepository<CheatEntity, Long> {
    List<CheatEntity> findAllByAccountOrPhone(String account, String phone);
}
