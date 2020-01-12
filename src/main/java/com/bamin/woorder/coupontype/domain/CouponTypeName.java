package com.bamin.woorder.coupontype.domain;

import com.bamin.woorder.coupontype.domain.exception.CouponTypeNameLengthException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class CouponTypeName {

    private static final int LENGTH_LOWER_BOUND = 1;
    private static final int LENGTH_UPPER_BOUND = 30;

    @Column(name = "coupon_usable_start_time",
            nullable = false,
            length = 30)
    private String couponTypeName;

    public CouponTypeName(final String couponTypeName) {
        checkNameLength(couponTypeName);
        this.couponTypeName = couponTypeName;
    }

    private void checkNameLength(final String couponTypeName) {
        if (couponTypeName.length() < LENGTH_LOWER_BOUND || couponTypeName.length() > LENGTH_UPPER_BOUND) {
            throw new CouponTypeNameLengthException(couponTypeName);
        }
    }
}
