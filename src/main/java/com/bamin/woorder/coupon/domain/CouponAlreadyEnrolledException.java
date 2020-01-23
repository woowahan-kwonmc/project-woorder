package com.bamin.woorder.coupon.domain;

public class CouponAlreadyEnrolledException extends RuntimeException {

    private static final String COUPON_ALREADY_ENROLLED_EXCEPTION_MESSAGE = "이미 등록된 쿠폰입니다.";

    CouponAlreadyEnrolledException() {
        super(COUPON_ALREADY_ENROLLED_EXCEPTION_MESSAGE);
    }
}
