package com.closet.stats.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "daily_stats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// 프론트에서 대시보드나 차트 이용, 빠른 조회를 위한 엔티티.
public class DailyStatsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 집계 기준 날짜
    @Column(nullable = false)
    private Date date;

    // FetchType.LAZY: 지연 로딩 옵션. 해당 엔티티를 사용할 때만 불러오는 어노테이션.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryStatsEntity category;

    // 옷 색상별(색상 번호, 색상명: 0-unknown)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id", nullable = false)
    private ColorStatsEntity color;

    // 옷 크기별(크기 번호, 크기명: 0-free)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "size_id", nullable = false)
    private SizeStatsEntity size;

    // 집계 데이터
    private Double avgPrice;
    private Integer maxPrice;
    private Integer minPrice;
    private Integer totalCount;
}
