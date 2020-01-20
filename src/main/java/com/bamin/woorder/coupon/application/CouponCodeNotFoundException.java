package com.bamin.woorder.coupon.application;

import lombok.Getter;

@Getter
public class CouponCodeNotFoundException extends CouponNotFoundException {

    private String requestCode;

    public CouponCodeNotFoundException(final String requestCode) {
        super();
        this.requestCode = requestCode;
    }
}
