package com.bamin.woorder.coupon.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CouponTypeData {

    private String name;
    private int discount;
    private long currentCount;
    private long totalCount;
    private boolean hasCode;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Builder
    public CouponTypeData(final String name,
                          final int discount,
                          final long currentCount,
                          final long totalCount,
                          final boolean hasCode,
                          final LocalDateTime startTime,
                          final LocalDateTime endTime) {
        this.name = name;
        this.discount = discount;
        this.currentCount = currentCount;
        this.totalCount = totalCount;
        this.hasCode = hasCode;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
