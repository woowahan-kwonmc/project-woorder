package com.bamin.woorder.coupon.domain;

public class AlreadyUsedCouponException extends RuntimeException {

    private static final String ALREADY_USED_COUPON_EXCEPTION_MESSAGE = "이미 사용된 쿠폰은 다시 사용할 수 없습니다.";

    AlreadyUsedCouponException() {
        super(ALREADY_USED_COUPON_EXCEPTION_MESSAGE);
    }
}
