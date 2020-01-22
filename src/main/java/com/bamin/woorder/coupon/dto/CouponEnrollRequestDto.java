package com.bamin.woorder.coupon.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CouponEnrollRequestDto {

    private Long memberNo;
    private String code;

    public CouponEnrollRequestDto(final Long memberNo, final String code) {
        this.memberNo = memberNo;
        this.code = code;
    }
}
