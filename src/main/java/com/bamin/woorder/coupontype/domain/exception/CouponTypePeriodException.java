package com.bamin.woorder.coupontype.domain.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CouponTypePeriodException extends RuntimeException {

    private static final String CANNOT_CREATE_COUPON_EXCEPTION_MESSAGE = "쿠폰 생성 가능 기간이 아닙니다.";

    private final LocalDateTime endTime;

    public CouponTypePeriodException(LocalDateTime endTime) {
        super(CANNOT_CREATE_COUPON_EXCEPTION_MESSAGE);
        this.endTime = endTime;
    }
}
