package com.bamin.woorder.coupontype.presentation;

class CouponTypeConstants {

    static final String CREATE_COUPON_TYPE_NAME = "민철이가 쏜다";
    static final String CREATE_COUPON_TYPE_COUNT = "100";
    static final String CREATE_COUPON_TYPE_DISCOUNT = "10000";

    static final int CREATE_COUPON_TYPE_COUNT_INT = 100;
    static final int CREATE_COUPON_TYPE_DISCOUNT_INT = 10000;

    static final int COUPON_TYPE_NO_NOT_EXIST = 1_000_000;

    static final int COUPON_TYPE_NO_01 = 1;
    static final int COUPON_TYPE_NO_01_DISCOUNT = 18000;
    static final int COUPON_TYPE_NO_01_COUNT = 100;
    static final String COUPON_TYPE_NO_01_NAME = "이달의 카피라이터";
    static final String COUPON_TYPE_NO_01_START_TIME = "2020-01-20T15:13:20";
    static final String COUPON_TYPE_NO_01_END_TIME = "2021-01-19T15:13:20";

    static final String COUPON_TYPE_CREATE_SUCCEED_RESPONSE_MESSAGE = "쿠폰 종류 생성 성공";
    static final String COUPON_TYPE_PERIOD_FAILED_RESPONSE_MESSAGE = "쿠폰 사용 가능 기간 설정을 확인해주세요.";
    static final String COUPON_TYPE_SELECT_SUCCEED_RESPONSE_MESSAGE = "쿠폰 타입 정보 조회 성공";
    static final String COUPON_TYPES_SELECT_SUCCEED_RESPONSE_MESSAGE = "쿠폰 타입 %d 페이지 %d 개 조회 성공";
    static final String COUPON_TYPE_SELECT_NOT_FOUND_RESPONSE_MESSAGE = "해당하는 쿠폰 타입이 존재하지 않습니다.";

    static final String COUPON_TYPE_BASE_SERVLET_PATH = "/api/v1/couponTypes";
    static final String COUPON_TYPE_ALL_SERVLET_PATH = "/api/v1/couponTypes/all";
    static final String COUPON_TYPE_SELECT_SERVLET_PATH = "/api/v1/couponTypes/{couponTypeNo}";

    static final String COUPON_TYPE_SELECT_FORMATTED_PATH = "/api/v1/couponTypes/%d";
}
