package com.bamin.woorder.coupon.domain;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Optional;

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

    String getCouponCode() {
        return Optional.ofNullable(couponCode).orElse(null);
    }
}
