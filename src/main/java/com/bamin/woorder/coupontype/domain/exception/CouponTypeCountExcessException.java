package com.bamin.woorder.coupontype.domain.exception;

import lombok.Getter;

@Getter
public class CouponTypeCountExcessException extends RuntimeException {

    private static final String COUPON_TYPE_COUNT_EXCESS_EXCEPTION_MESSAGE = "생성 가능한 수를 초과하여 생성할 수 없습니다.";

    private final Long currentCount;
    private final Long requestCount;
    private final Long limitCount;

    public CouponTypeCountExcessException(final Long currentCount, final Long requestCount, final Long limitCount) {
        super(COUPON_TYPE_COUNT_EXCESS_EXCEPTION_MESSAGE);
        this.currentCount = currentCount;
        this.requestCount = requestCount;
        this.limitCount = limitCount;
    }
}
