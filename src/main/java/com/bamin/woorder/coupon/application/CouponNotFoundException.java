package com.bamin.woorder.coupon.application;

class CouponNotFoundException extends RuntimeException {

    private static final String COUPON_NOT_FOUND_EXCEPTION_MESSAGE = "해당하는 쿠폰이 존재하지 않습니다.";

    CouponNotFoundException() {
        super(COUPON_NOT_FOUND_EXCEPTION_MESSAGE);
    }
}
