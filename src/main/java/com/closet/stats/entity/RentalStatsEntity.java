package com.closet.stats.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "rental_stats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
// 원본 시세 기록
public class RentalStatsEntity {
    /*
    * 예를 들어 9월 28일에 대여하기 위해 2알치 200원을 결제함.
    * 그러면 9월 28일 부터 9월 29일까지 100원이 결제된 것.
    * 해당 옷이 캐주얼 종류, 알 수 없는 색, free 사이즈라는 속성을 가지고 있다면,
    * 각 테이블에 날짜와 평균가, 총 개수, id, 이름이 저장됨.
    *
    * 실시간 대여 원본 기록은 RentalStatusEntity에 저장하고
    * 빠른 조회와 그래프 표시를 용이하게 하기 위해 조회는 DailyStatsEntity에서 함.
    * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 집계 기준 날짜
    @Column(nullable = false)
    private Date statDate;

    // 옷 카테고리별 (카테고리 번호, 카테고리명: 0-casual)
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
    @Column(nullable = false)
    private Integer price;

    //대여일
    @Column(nullable = false)
    private Date rentalDate;
}
