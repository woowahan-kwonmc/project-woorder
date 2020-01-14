package com.bamin.woorder.coupon.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CouponData {

    private String code;
    private String status;

    @Builder
    public CouponData(String code, String status) {
        this.code = code;
        this.status = status;
    }
}
