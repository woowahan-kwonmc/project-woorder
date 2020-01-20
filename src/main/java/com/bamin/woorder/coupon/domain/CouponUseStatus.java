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
class CouponUseStatus {

    @Column(name = "coupon_status",
            nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CouponStatus couponStatus;

    CouponUseStatus(final CouponStatus couponStatus) {
        this.couponStatus = couponStatus;
    }

    void useCoupon() {
        checkCouponUsed();
        this.couponStatus = CouponStatus.USED;
    }

    private void checkCouponUsed() {
        if (this.couponStatus.equals(CouponStatus.USED)) {
            throw new AlreadyUsedCouponException();
        }
    }
}
