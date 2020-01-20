package com.bamin.woorder.coupontype.application;

import lombok.Getter;

@Getter
public class CouponTypeNotFoundException extends RuntimeException {

    private static final String COUPON_NOT_FOUND_EXCEPTION_MESSAGE = "해당하는 쿠폰 타입이 존재하지 않습니다.";

    private final Long requestNo;

    CouponTypeNotFoundException(final Long requestNo) {
        super(COUPON_NOT_FOUND_EXCEPTION_MESSAGE);
        this.requestNo = requestNo;
    }
}
