package com.closet.board.repository;

import com.closet.board.entity.SellEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellBoardRepository extends JpaRepository<SellEntity, Long> {
    List<SellEntity> findAllByOrderByCreatedAtDesc();
}
