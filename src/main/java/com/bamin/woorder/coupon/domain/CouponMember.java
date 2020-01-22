package com.bamin.woorder.coupon.domain;

import com.bamin.woorder.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class CouponMember {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_member_no")
    private Member couponMember;

    public CouponMember(final Member couponMember) {
        this.couponMember = couponMember;
    }

    boolean isNotEquals(final Member payMember) {
        return !couponMember.equals(payMember);
    }

    public String getName() {
        return couponMember.getName();
    }
}
