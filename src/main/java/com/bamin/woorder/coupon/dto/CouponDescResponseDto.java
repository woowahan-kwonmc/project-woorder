package com.bamin.woorder.coupon.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CouponDescResponseDto {

    private String code;
    private String useStatus;
    private String name;
    private int discount;
    private String memberName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Builder
    public CouponDescResponseDto(final String code,
                                 final String useStatus,
                                 final String name,
                                 final int discount,
                                 final String memberName,
                                 final LocalDateTime startTime,
                                 final LocalDateTime endTime) {
        this.code = code;
        this.useStatus = useStatus;
        this.name = name;
        this.discount = discount;
        this.memberName = memberName;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
