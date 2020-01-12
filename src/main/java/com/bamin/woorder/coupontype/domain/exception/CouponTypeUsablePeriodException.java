package com.bamin.woorder.coupontype.domain.exception;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CouponTypeUsablePeriodException extends RuntimeException {

    private static final String INVALID_COUPON_TYPE_USABLE_PERIOD_EXCEPTION_MESSAGE = "쿠폰 사용 가능 기간 설정을 확인해주세요.";

    private final LocalDateTime currentTime;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public CouponTypeUsablePeriodException(final LocalDateTime currentTime, final LocalDateTime startTime, final LocalDateTime endTime) {
        super(INVALID_COUPON_TYPE_USABLE_PERIOD_EXCEPTION_MESSAGE);
        this.currentTime = currentTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
