package com.bamin.woorder.coupon.domain;

import com.bamin.woorder.payment.domain.Payment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class CouponPayment {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_payment_no")
    private Payment couponPayment;

    public CouponPayment(final Payment couponPayment) {
        this.couponPayment = couponPayment;
    }
}
