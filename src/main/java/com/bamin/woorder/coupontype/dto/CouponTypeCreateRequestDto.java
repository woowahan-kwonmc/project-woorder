package com.bamin.woorder.coupontype.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CouponTypeCreateRequestDto implements CouponTypeRequestDto {

    private String couponTypeName;
    private String couponTypeCount;
    private String couponTypeDiscount;
    private String startTime;
    private String endTime;

    public CouponTypeCreateRequestDto(final String couponTypeName,
                                      final String couponTypeCount,
                                      final String couponTypeDiscount,
                                      final String startTime,
                                      final String endTime) {
        this.couponTypeName = couponTypeName;
        this.couponTypeCount = couponTypeCount;
        this.couponTypeDiscount = couponTypeDiscount;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
