package com.closet.board.repository;

import com.closet.board.entity.FavoriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Long> {
    Optional<FavoriteEntity> findByUserIdAndProductId(Long userId, Long productId);
    List<FavoriteEntity> findByUserId(Long userId);
    Long countByProductId(Long productId);
}
