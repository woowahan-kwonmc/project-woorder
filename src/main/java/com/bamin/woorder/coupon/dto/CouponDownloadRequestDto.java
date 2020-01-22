package com.bamin.woorder.coupon.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CouponDownloadRequestDto {

    private Long memberNo;
    private Long couponTypeNo;

    public CouponDownloadRequestDto(final Long memberNo, final Long couponTypeNo) {
        this.memberNo = memberNo;
        this.couponTypeNo = couponTypeNo;
    }
}
