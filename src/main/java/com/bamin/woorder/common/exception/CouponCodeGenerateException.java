package com.bamin.woorder.common.exception;

public class CouponCodeGenerateException extends RuntimeException {

    public static final String COUPON_CODE_GENERATE_EXCEPTION_MESSAGE = "쿠폰 코드 생성에 문제가 생겼습니다.";

    public CouponCodeGenerateException() {
        super(COUPON_CODE_GENERATE_EXCEPTION_MESSAGE);
    }
}
