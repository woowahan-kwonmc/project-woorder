package com.bamin.woorder.coupontype.domain.exception;

public class CouponTypeDiscountAmountException extends RuntimeException {

    private static final String COUPON_TYPE_DISCOUNT_AMOUNT_EXCEPTION_MESSAGE = "쿠폰 타입의 할인 가격은 0원을 초과 해야 합니다.";

    public CouponTypeDiscountAmountException() {
        super(COUPON_TYPE_DISCOUNT_AMOUNT_EXCEPTION_MESSAGE);
    }
}
