package com.bamin.woorder.coupon.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CouponResponseDto {

    private List<CouponData> coupons;
    private CouponTypeData couponType;

    @Builder
    public CouponResponseDto(final List<CouponData> coupons,
                             final String couponTypeName,
                             final int couponTypeDiscount,
                             final long couponTypeCurrentCount,
                             final long couponTypeCount,
                             final boolean couponTypeHasCode,
                             final LocalDateTime couponTypeStartTime,
                             final LocalDateTime couponTypeEndTime) {
        this.coupons = coupons;
        this.couponType = CouponTypeData.builder()
                .name(couponTypeName)
                .discount(couponTypeDiscount)
                .currentCount(couponTypeCurrentCount)
                .totalCount(couponTypeCount)
                .hasCode(couponTypeHasCode)
                .startTime(couponTypeStartTime)
                .endTime(couponTypeEndTime)
                .build();
    }
}
