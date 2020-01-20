package com.bamin.woorder.coupon.application;

import lombok.Getter;

@Getter
public class CouponNoNotFoundException extends CouponNotFoundException {

    private Long requestNo;

    CouponNoNotFoundException(final Long requestNo) {
        super();
        this.requestNo = requestNo;
    }
}
