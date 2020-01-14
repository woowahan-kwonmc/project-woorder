package com.bamin.woorder.coupontype.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class CouponTypeHasCode {

    @Column(name = "coupon_type_has_code",
            nullable = false)
    private boolean couponTypeHasCode;

    public CouponTypeHasCode(final boolean couponTypeHasCode) {
        this.couponTypeHasCode = couponTypeHasCode;
    }
}
