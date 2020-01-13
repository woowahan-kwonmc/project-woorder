package com.bamin.woorder.coupon.domain;

import com.bamin.woorder.coupontype.domain.CouponType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class CouponTypeInfo {

    @ManyToOne(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_type_no")
    private CouponType couponType;

    public CouponTypeInfo(final CouponType couponType) {
        this.couponType = couponType;
    }
}
