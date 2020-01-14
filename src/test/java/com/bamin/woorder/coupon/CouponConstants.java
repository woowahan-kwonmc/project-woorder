package com.bamin.woorder.coupon;

public class CouponConstants {

    public static final long DOWNLOAD_COUPON_TYPE_NO = 4L;
    public static final long CODE_COUPON_TYPE_NO = 1L;
    public static final long PAST_PERIOD_COUPON_TYPE_NO = 5L;

    public static final String CREATE_COUPON_DOWNLOAD_MODE_SERVLET_PATH = "/api/v1/coupons/downloadMode";
    public static final String CREATE_COUPON_CODE_MODE_SERVLET_PATH = "/api/v1/coupons/codeMode";

    public static final String CREATE_COUPON_SUCCEED_RESPONSE_MESSAGE = "다운로드 쿠폰 생성 성공";
    public static final String CREATE_CODE_COUPON_SUCCEED_RESPONSE_MESSAGE = "코드 쿠폰 생성 성공";
    public static final String CREATE_COUPON_FAILED_EXCESS_RESPONSE_MESSAGE = "생성 가능한 수를 초과하여 생성할 수 없습니다.";
    public static final String CREATE_COUPON_FAILED_CODE_NEEDED_RESPONSE_MESSAGE = "코드가 필요한 코드형 쿠폰 타입입니다.";
    public static final String CREATE_COUPON_FAILED_CREATABLE_PERIOD_RESPONSE_MESSAGE = "쿠폰 생성 가능 기간이 아닙니다.";
}
