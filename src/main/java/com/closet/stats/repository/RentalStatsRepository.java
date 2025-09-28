package com.closet.stats.repository;

import com.closet.stats.bean.PriceStats;
import com.closet.stats.entity.CategoryStatsEntity;
import com.closet.stats.entity.ColorStatsEntity;
import com.closet.stats.entity.RentalStatsEntity;
import com.closet.stats.entity.SizeStatsEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
//세부 원인 분석을 위한 원시 데이터 저장 엔티티.
public interface RentalStatsRepository extends JpaRepository<RentalStatsEntity, Long> {
    // 특정 날짜 구간 동안 검색 기능. 실시간 데이터 분석이 가능함.
    // TODO: 시작일과 종료일간의 간격이 너무 길면 데이터 양에 따라 성능 저하 등이 발생할 우려가 있음. 추후 테스트 요망.
    @Query("""
        SELECT r.rentalDate, r.category, r.color, r.size,
               AVG(r.price), MAX(r.price), MIN(r.price), COUNT(r.id)
        FROM RentalStatsEntity r
        WHERE (:category IS NULL OR r.category = :category)
          AND (:color IS NULL OR r.color = :color)
          AND (:size IS NULL OR r.size = :size)
          AND r.rentalDate BETWEEN :startDate AND :endDate
        GROUP BY r.rentalDate, r.category, r.color, r.size
    """)
    List<PriceStats> findStatsByDateRange(@Param("startDate") Date startDate,
                                        @Param("endDate") Date endDate,
                                        @Param("category") CategoryStatsEntity category,
                                        @Param("color") ColorStatsEntity color,
                                        @Param("size") SizeStatsEntity size);
}
