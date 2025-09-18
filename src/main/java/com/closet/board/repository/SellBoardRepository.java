package com.closet.board.repository;

import com.closet.board.entity.SellEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellBoardRepository extends JpaRepository<SellEntity, Long> {
    List<SellEntity> findAllByOrderByCreatedAtDesc();
}
