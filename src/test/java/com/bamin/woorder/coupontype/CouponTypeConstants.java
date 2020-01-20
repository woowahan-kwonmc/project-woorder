package com.bamin.woorder.coupontype;

import com.bamin.woorder.coupontype.domain.CouponType;

import java.time.LocalDateTime;

public class CouponTypeConstants {

    public static final String COUPON_TYPE_NAME = "민철이가 쏜다.";
    public static final String COUPON_TYPE_COUNT = "10";
    public static final String COUPON_TYPE_DISCOUNT = "1000";
    public static final boolean COUPON_TYPE_HAS_CODE_CODE_TYPE = true;

    public static final CouponType TEST_COUPON_TYPE_DISCOUNT_1000 = CouponType.builder()
            .couponTypeName(COUPON_TYPE_NAME)
            .couponTypeCount(COUPON_TYPE_COUNT)
            .couponTypeDiscount(COUPON_TYPE_DISCOUNT)
            .couponTypeHasCode(COUPON_TYPE_HAS_CODE_CODE_TYPE)
            .startTime(LocalDateTime.now().plusDays(2L).toString())
            .endTime(LocalDateTime.now().plusDays(3L).toString())
            .build();

    public static final CouponType TEST_COUPON_TYPE_DISCOUNT_200000 = CouponType.builder()
            .couponTypeName(COUPON_TYPE_NAME)
            .couponTypeCount(COUPON_TYPE_COUNT)
            .couponTypeDiscount("200000")
            .couponTypeHasCode(COUPON_TYPE_HAS_CODE_CODE_TYPE)
            .startTime(LocalDateTime.now().plusDays(2L).toString())
            .endTime(LocalDateTime.now().plusDays(3L).toString())
            .build();
}
