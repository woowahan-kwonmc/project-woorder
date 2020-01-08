package com.bamin.woorder.coupon.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class CouponUseStatus {

    @Column(name = "coupon_status",
            nullable = false)
    private CouponStatus couponStatus;

    public CouponUseStatus(final CouponStatus couponStatus) {
        this.couponStatus = couponStatus;
    }
}
