package com.bamin.woorder.coupontype.domain;

import com.bamin.woorder.coupon.domain.Coupon;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
class CouponTypeCoupons {

    @OneToMany(mappedBy = "couponTypeInfo", fetch = FetchType.LAZY)
    private List<Coupon> coupons = new ArrayList<>();
}
