package com.bamin.woorder.coupontype.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CouponTypeResponseDto {

    private Long no;
    private String name;
    private int discount;
    private long count;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Builder
    public CouponTypeResponseDto(final Long no,
                                 final String name,
                                 final int discount,
                                 final long count,
                                 final LocalDateTime startTime,
                                 final LocalDateTime endTime) {
        this.no = no;
        this.name = name;
        this.discount = discount;
        this.count = count;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
