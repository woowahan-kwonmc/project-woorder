package com.bamin.woorder.coupontype.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class CouponTypeCount {

    @Column(name = "coupon_count",
            nullable = false)
    private int count;

    public CouponTypeCount(final int count) {
        this.count = count;
    }
}
