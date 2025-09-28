package com.closet.stats.repository;

import com.closet.stats.entity.CategoryStatsEntity;
import com.closet.stats.entity.ColorStatsEntity;
import com.closet.stats.entity.DailyStatsEntity;
import com.closet.stats.entity.SizeStatsEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface DailyStatsRepository extends JpaRepository<DailyStatsEntity, Long> {
//    @Query("""
//        SELECT d
//        FROM DailyStatsEntity d
//        WHERE (:category IS NULL OR d.category.name = :category)
//          AND (:color IS NULL OR d.color.name = :color)
//          AND (:size IS NULL OR d.size.name = :size)
//          AND (:startDate IS NULL OR d.date >= :startDate)
//          AND (:endDate IS NULL OR d.date <= :endDate)
//        ORDER BY d.date ASC
//    """)
//    List<DailyStatsEntity> findDailyStats(
//            @Param("category") String category,
//            @Param("color") String color,
//            @Param("size") String size,
//            @Param("startDate") Date startDate,
//            @Param("endDate") Date endDate
//    );

    Optional<DailyStatsEntity> findByDateAndCategoryAndColorAndSize(
            Date date,
            CategoryStatsEntity category,
            ColorStatsEntity color,
            SizeStatsEntity size
    );
}
