package com.bamin.woorder.coupon.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CouponExistDto {

    private boolean exists;

    public CouponExistDto(final boolean exists) {
        this.exists = exists;
    }
}
