package com.bamin.woorder.coupontype.domain.exception;

import lombok.Getter;

@Getter
public class CouponTypeNameLengthException extends RuntimeException {

    public static final String COUPON_TYPE_NAME_LENGTH_EXCEPTION_MESSAGE = "쿠폰 타입의 이름은 1자 이상 30자 미만입니다.";
    private final String requestedName;

    public CouponTypeNameLengthException(final String requestedName) {
        super(COUPON_TYPE_NAME_LENGTH_EXCEPTION_MESSAGE);
        this.requestedName = requestedName;
    }
}
