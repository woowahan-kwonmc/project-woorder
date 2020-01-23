package com.bamin.woorder.coupon.domain;

import com.bamin.woorder.coupontype.domain.CouponType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class CouponTypeInfo {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_type_no")
    private CouponType couponType;

    CouponTypeInfo(final CouponType couponType) {
        this.couponType = couponType;
    }

    int getDiscount() {
        return couponType.getDiscount();
    }

    public String getName() {
        return couponType.getName();
    }

    public LocalDateTime getStartTime() {
        return couponType.getStartTime();
    }

    public LocalDateTime getEndTime() {
        return couponType.getEndTime();
    }

    public void checkEnrollPeriod() {
        couponType.checkEnrollPeriod();
    }
}
