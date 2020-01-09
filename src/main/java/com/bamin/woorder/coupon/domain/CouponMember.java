package com.bamin.woorder.coupon.domain;

import com.bamin.woorder.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class CouponMember {

    @ManyToOne
    @JoinColumn(name = "\"coupon_member_no\"")
    private Member couponMember;

    public CouponMember(final Member couponMember) {
        this.couponMember = couponMember;
    }
}
