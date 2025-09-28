package com.closet.stats.bean;

import com.closet.stats.entity.CategoryStatsEntity;
import com.closet.stats.entity.ColorStatsEntity;
import com.closet.stats.entity.SizeStatsEntity;

import java.util.Date;

// rental과 daily간 데이터 이동을 위한 인터페이스.
public interface PriceStats {
    Date getRentalDate();

    CategoryStatsEntity getCategory();
    ColorStatsEntity getColor();
    SizeStatsEntity getSize();

    Double getAvgPrice();
    Integer getMaxPrice();
    Integer getMinPrice();
    Integer getCount();
}
