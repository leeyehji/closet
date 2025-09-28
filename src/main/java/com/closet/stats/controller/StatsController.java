package com.closet.stats.controller;

import com.closet.stats.bean.PriceStats;
import com.closet.stats.entity.*;
import com.closet.stats.service.DailyStatsService;
import com.closet.stats.service.RentalStatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/stats")
@RequiredArgsConstructor
public class StatsController {
    private final RentalStatsService rentalHistoryService;
    private final DailyStatsService dailyStatsService;
    private final RentalStatsService rentalStatsService;

    // 데일리 통계 조회. 모든 유저 사용 가능.
    // 빠른 조회. 대시보드 및 차트용.
    // TODO: 나중에 결제 서비스 API나 프론트 확인하여 엔티티대신 스트링이나 Long으로 데이터 구조 변경 가능.
    @GetMapping("/daily")
    public ResponseEntity<List<DailyStatsEntity>> getDailyStats(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date,
            // 통계 조회 시 카테고리, 색상, 사이즈 개별 및 혼용 검색 가능
            @RequestParam(required = false) CategoryStatsEntity category,
            @RequestParam(required = false) ColorStatsEntity color,
            @RequestParam(required = false) SizeStatsEntity size) {

        List<DailyStatsEntity> stats = dailyStatsService.getDailyStats(date, category, color, size);
        return ResponseEntity.ok(stats);
    }

    // 렌탈 통계 조회. 관리자 페이지에서 사용 예정.
    // 모든 세부 정보 조회 가능. 원인 분석용.
    // TODO: 나중에 결제 서비스 API나 프론트 확인하여 엔티티대신 스트링이나 Long으로 데이터 구조 변경 가능.
    @GetMapping("/rental")
    public ResponseEntity<List<PriceStats>> getRentalStats(
            // @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
            // 통계 조회 시 카테고리, 색상, 사이즈 개별 및 혼용 검색 가능
            @RequestParam(required = false) CategoryStatsEntity category,
            @RequestParam(required = false) ColorStatsEntity color,
            @RequestParam(required = false) SizeStatsEntity size) {
        List<PriceStats> stats = rentalStatsService.getRentalStats(startDate, endDate, category, color, size);
        return ResponseEntity.ok(stats);
    }
}
