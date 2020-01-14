package com.bamin.woorder.coupon.application;

public class CouponCodeDuplicatedException extends RuntimeException {

    public CouponCodeDuplicatedException() {
        super("쿠폰 코드 중복, 재생성합니다.");
    }
}
