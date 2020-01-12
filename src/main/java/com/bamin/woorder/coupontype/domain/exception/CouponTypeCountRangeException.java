package com.bamin.woorder.coupontype.domain.exception;

public class CouponTypeCountRangeException extends RuntimeException {

    private static final String COUPON_TYPE_COUNT_RANGE_EXCEPTION_MESSAGE = "쿠폰 타입의 수량은 최소 1개 입니다.";

    public CouponTypeCountRangeException() {
        super(COUPON_TYPE_COUNT_RANGE_EXCEPTION_MESSAGE);
    }
}
