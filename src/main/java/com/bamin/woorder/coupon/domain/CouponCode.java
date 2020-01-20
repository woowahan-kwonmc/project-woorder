package com.bamin.woorder.coupon.domain;


import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
class CouponCode {

    @Column(name = "coupon_code",
            length = 45,
            unique = true)
    private String couponCode;

    CouponCode() {
    }

    CouponCode(final String couponCode) {
        this.couponCode = couponCode;
    }
}
