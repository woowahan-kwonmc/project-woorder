package com.bamin.woorder.coupon.domain;

import com.bamin.woorder.coupontype.domain.CouponType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class CouponTypeInfo {

    @JoinColumn(name = "coupon_type_no")
    @ManyToOne
    private CouponType couponType;

    public CouponTypeInfo(final CouponType couponType) {
        this.couponType = couponType;
    }
}
