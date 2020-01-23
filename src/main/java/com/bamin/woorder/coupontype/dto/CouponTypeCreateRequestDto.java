package com.bamin.woorder.coupontype.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CouponTypeCreateRequestDto implements CouponTypeRequestDto {

    private String name;
    private String count;
    private String discount;
    private boolean hasCode;
    private String startTime;
    private String endTime;

    public CouponTypeCreateRequestDto(final String name,
                                      final String count,
                                      final String discount,
                                      final boolean couponTypeHasCode,
                                      final String startTime,
                                      final String endTime) {
        this.name = name;
        this.count = count;
        this.discount = discount;
        this.hasCode = couponTypeHasCode;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
