package com.closet.stats.service;

import com.closet.stats.bean.PriceStats;
import com.closet.stats.entity.CategoryStatsEntity;
import com.closet.stats.entity.ColorStatsEntity;
import com.closet.stats.entity.SizeStatsEntity;
import com.closet.stats.repository.RentalStatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalStatsService {
    private final RentalStatsRepository rentalStatsRepository;

    public List<PriceStats> getRentalStats(Date startDate, Date endDate,
                                           CategoryStatsEntity category, ColorStatsEntity color, SizeStatsEntity size) {
        return rentalStatsRepository.findStatsByDateRange(startDate, endDate, category, color, size);
    }
}
