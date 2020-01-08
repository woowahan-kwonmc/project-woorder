package com.bamin.woorder.coupontype.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class CouponTypeDiscount {

    @Column(name = "coupon_type_discount",
            nullable = false)
    private int amount;

    public CouponTypeDiscount(final int amount) {
        this.amount = amount;
    }
}
