package com.bamin.woorder.coupon.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class CouponMember {

    @Column(name = "\"coupon_member_no\"",
            nullable = false,
            updatable = false)
    private Long couponMember;

    public CouponMember(final Long couponMember) {
        this.couponMember = couponMember;
    }
}
