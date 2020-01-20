package com.bamin.woorder.coupon.domain;

public class InvalidCouponOwnerException extends RuntimeException {

    private static final String COUPON_OWNER_EXCEPTION_MESSAGE = "자신이 소유한 쿠폰만 사용할 수 있습니다.";

    InvalidCouponOwnerException() {
        super(COUPON_OWNER_EXCEPTION_MESSAGE);
    }
}
