package com.closet.config;

import com.closet.stats.service.DailyStatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

// TODO: stats 페이지에 넣거나 스케줄러 패키지에 넣어야 함.
@Component
@RequiredArgsConstructor
public class DailyStatsScheduler {

    private final DailyStatsService dailyStatsService;

    // 매일 0시마다 어제 데이터 갱신
    @Scheduled(cron = "0 0 0 * * *")
    public void updateYesterdayStats() {
        LocalDate yesterdayLocal = LocalDate.now().minusDays(1);
        Date yesterday = Date.from(
                yesterdayLocal.atStartOfDay(ZoneId.systemDefault()).toInstant()
        );
        dailyStatsService.updateDailyStats(yesterday, yesterday);
    }
}
