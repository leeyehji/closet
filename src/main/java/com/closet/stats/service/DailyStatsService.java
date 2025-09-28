package com.closet.stats.service;

import com.closet.stats.bean.PriceStats;
import com.closet.stats.entity.CategoryStatsEntity;
import com.closet.stats.entity.ColorStatsEntity;
import com.closet.stats.entity.DailyStatsEntity;
import com.closet.stats.entity.SizeStatsEntity;
import com.closet.stats.repository.DailyStatsRepository;
import com.closet.stats.repository.RentalStatsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyStatsService {
    private final RentalStatsRepository rentalStatsRepository;
    private final DailyStatsRepository dailyStatsRepository;

    /**
     * 특정 날짜 범위의 DailyStats를 갱신
     */
    @Transactional
    public void updateDailyStats(Date startDate, Date endDate) {
        // 날짜별, 카테고리/색상/사이즈별로 그룹핑
        List<PriceStats> stats = rentalStatsRepository.findStatsByDateRange(startDate, endDate, null, null, null);
        for (PriceStats p: stats) {
            Date date = p.getRentalDate();
            CategoryStatsEntity category = p.getCategory();
            ColorStatsEntity color = p.getColor();
            SizeStatsEntity size = p.getSize();
            Double avgPrice = p.getAvgPrice();
            Integer maxPrice = p.getMaxPrice();
            Integer minPrice = p.getMinPrice();
            Integer totalCount = p.getCount();

            // 기존 DailyStats 있는지 확인 후 저장. 이 때문에 List 대신 Optional 사용.
            DailyStatsEntity daily = dailyStatsRepository.findByDateAndCategoryAndColorAndSize(
                    date, category, color, size
            ).orElse(DailyStatsEntity.builder()
                    .date(date)
                    .category(category)
                    .color(color)
                    .size(size)
                    .build()
            );

            daily.setAvgPrice(avgPrice);
            daily.setMaxPrice(maxPrice);
            daily.setMinPrice(minPrice);
            daily.setTotalCount(totalCount);

            dailyStatsRepository.save(daily);
        }
    }

    // 특정 날짜의 정보 가져옴.
    // TODO: 프론트에서 필요한 정보 양에 따라 수정 필요.
    public List<DailyStatsEntity> getDailyStats(Date date, CategoryStatsEntity category, ColorStatsEntity color, SizeStatsEntity size) {
        return dailyStatsRepository.findByDateAndCategoryAndColorAndSize(date, category, color, size)
                .map(Collections::singletonList) // Optional -> List
                .orElse(Collections.emptyList());
    }

}
