package com.bamin.woorder.payment.domain;

import com.bamin.woorder.coupon.domain.Coupon;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class PaymentCoupons {

    @OneToMany(mappedBy = "couponPayment")
    private List<Coupon> coupons = new ArrayList<>();
}
