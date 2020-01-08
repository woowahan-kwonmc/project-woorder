package com.bamin.woorder.coupon.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class CouponCode {

    @Column(name = "coupon_code",
            nullable = false,
            unique = true)
    private String couponCode;

    public CouponCode(final String couponCode) {
        this.couponCode = couponCode;
    }
}
