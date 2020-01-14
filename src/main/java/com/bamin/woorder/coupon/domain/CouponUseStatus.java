package com.bamin.woorder.coupon.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class CouponUseStatus {

    @Column(name = "coupon_status",
            nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CouponStatus couponStatus;

    public CouponUseStatus(final CouponStatus couponStatus) {
        this.couponStatus = couponStatus;
    }
}
