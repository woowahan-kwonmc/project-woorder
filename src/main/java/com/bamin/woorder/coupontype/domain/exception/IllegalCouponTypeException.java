package com.bamin.woorder.coupontype.domain.exception;

import lombok.Getter;

@Getter
public class IllegalCouponTypeException extends RuntimeException {

    private static final String COUPON_CODE_NEED_EXCEPTION_MESSAGE = "코드가 필요한 코드형 쿠폰 타입입니다.";
    private static final String COUPON_CODE_NOT_NEED_EXCEPTION_MESSAGE = "코드가 필요없는 다운로드형 쿠폰 타입 입니다.";

    private final Long no;
    private final String name;
    private final boolean hasCode;

    public IllegalCouponTypeException(final Long no, final String name, final boolean hasCode) {
        super(createMessage(hasCode));
        this.no = no;
        this.name = name;
        this.hasCode = hasCode;
    }

    private static String createMessage(final boolean hasCode) {
        if (hasCode) {
            return COUPON_CODE_NEED_EXCEPTION_MESSAGE;
        }
        return COUPON_CODE_NOT_NEED_EXCEPTION_MESSAGE;
    }
}
