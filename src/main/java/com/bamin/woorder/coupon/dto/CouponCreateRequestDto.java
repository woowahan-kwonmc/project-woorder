package com.bamin.woorder.coupon.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CouponCreateRequestDto implements CouponRequestDto {

    private Long couponTypeNo;
    private Long requestCounts;

    public CouponCreateRequestDto(final Long couponTypeNo, final Long requestCounts) {
        this.couponTypeNo = couponTypeNo;
        this.requestCounts = requestCounts;
    }
}