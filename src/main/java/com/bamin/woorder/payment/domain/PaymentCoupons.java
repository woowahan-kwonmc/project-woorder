package com.bamin.woorder.payment.domain;

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
class PaymentCoupons {

    @OneToMany(mappedBy = "couponPayment", fetch = FetchType.LAZY)
    private List<Coupon> coupons = new ArrayList<>();

    static PaymentCoupons init(final List<Coupon> payCoupons) {
        return new PaymentCoupons().addAll(payCoupons);
    }

    private PaymentCoupons addAll(final List<Coupon> payCoupons) {
        this.coupons.addAll(payCoupons);
        return this;
    }
}
