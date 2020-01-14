package com.bamin.woorder.coupontype.domain;

import com.bamin.woorder.coupontype.domain.exception.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CouponTypeTest {

    private static final String COUPON_TYPE_NAME = "민철이가 쏜다.";
    private static final String COUPON_TYPE_COUNT = "10";
    private static final String COUPON_TYPE_DISCOUNT = "1000";
    private static final boolean COUPON_TYPE_HAS_CODE_CODE_TYPE = true;

    @Test
    @DisplayName("CouponType 생성 성공")
    void successfullyConstruct() {
        assertDoesNotThrow(() -> CouponType.builder()
                .couponTypeName(COUPON_TYPE_NAME)
                .couponTypeCount(COUPON_TYPE_COUNT)
                .couponTypeDiscount(COUPON_TYPE_DISCOUNT)
                .couponTypeHasCode(COUPON_TYPE_HAS_CODE_CODE_TYPE)
                .startTime(LocalDateTime.now().plusDays(2L).toString())
                .endTime(LocalDateTime.now().plusDays(3L).toString())
                .build());
    }

    @Test
    @DisplayName("CouponType 생성 실패, 이름")
    void failedConstructWithName() {
        assertThrows(CouponTypeNameLengthException.class, () -> CouponType.builder()
                .couponTypeName("")
                .couponTypeCount(COUPON_TYPE_COUNT)
                .couponTypeDiscount(COUPON_TYPE_DISCOUNT)
                .couponTypeHasCode(COUPON_TYPE_HAS_CODE_CODE_TYPE)
                .startTime(LocalDateTime.now().plusDays(2L).toString())
                .endTime(LocalDateTime.now().plusDays(3L).toString())
                .build());
    }

    @Test
    @DisplayName("CouponType 생성 실패, 수량")
    void failedConstructWithCount() {
        assertThrows(CouponTypeCountRangeException.class, () -> CouponType.builder()
                .couponTypeName(COUPON_TYPE_NAME)
                .couponTypeCount("0")
                .couponTypeDiscount(COUPON_TYPE_DISCOUNT)
                .couponTypeHasCode(COUPON_TYPE_HAS_CODE_CODE_TYPE)
                .startTime(LocalDateTime.now().plusDays(2L).toString())
                .endTime(LocalDateTime.now().plusDays(3L).toString())
                .build());
    }

    @Test
    @DisplayName("CouponType 생성 실패, 가격")
    void failedConstructWithDiscount() {
        assertThrows(CouponTypeDiscountAmountException.class, () -> CouponType.builder()
                .couponTypeName(COUPON_TYPE_NAME)
                .couponTypeCount(COUPON_TYPE_COUNT)
                .couponTypeDiscount("0")
                .couponTypeHasCode(COUPON_TYPE_HAS_CODE_CODE_TYPE)
                .startTime(LocalDateTime.now().plusDays(2L).toString())
                .endTime(LocalDateTime.now().plusDays(3L).toString())
                .build());
    }

    @Test
    @DisplayName("CouponType 생성 실패, 기간")
    void failedConstructWithPeriod() {
        assertThrows(CouponTypeUsablePeriodException.class, () -> CouponType.builder()
                .couponTypeName(COUPON_TYPE_NAME)
                .couponTypeCount(COUPON_TYPE_COUNT)
                .couponTypeDiscount(COUPON_TYPE_DISCOUNT)
                .couponTypeHasCode(COUPON_TYPE_HAS_CODE_CODE_TYPE)
                .startTime(LocalDateTime.now().minusDays(2L).toString())
                .endTime(LocalDateTime.now().plusDays(3L).toString())
                .build());
    }

    @Test
    @DisplayName("CouponType, Coupon 생성 가능 기간 확인")
    void successfullyCheckCreatablePeriod() {
        CouponType couponType = CouponType.builder()
                .couponTypeName(COUPON_TYPE_NAME)
                .couponTypeCount(COUPON_TYPE_COUNT)
                .couponTypeDiscount(COUPON_TYPE_DISCOUNT)
                .couponTypeHasCode(COUPON_TYPE_HAS_CODE_CODE_TYPE)
                .startTime(LocalDateTime.now().plusDays(2L).toString())
                .endTime(LocalDateTime.now().plusDays(3L).toString())
                .build();
        assertThat(couponType.checkCreatablePeriod()).isTrue();
    }

    @Test
    @DisplayName("CouponType, Coupon 생성 가능 기간 확인 성공")
    void successfullyCheckCouponTypeCountExcess() {
        CouponType couponType = CouponType.builder()
                .couponTypeName(COUPON_TYPE_NAME)
                .couponTypeCount(COUPON_TYPE_COUNT)
                .couponTypeDiscount(COUPON_TYPE_DISCOUNT)
                .couponTypeHasCode(COUPON_TYPE_HAS_CODE_CODE_TYPE)
                .startTime(LocalDateTime.now().plusDays(2L).toString())
                .endTime(LocalDateTime.now().plusDays(3L).toString())
                .build();
        assertThat(couponType.getTotalCountAfterGenerate(1L, 1L)).isEqualTo(2L);
    }

    @Test
    @DisplayName("CouponType, Coupon 생성 가능 기간 확인 실패, 초과")
    void failedCheckCouponTypeCountExcess() {
        CouponType couponType = CouponType.builder()
                .couponTypeName(COUPON_TYPE_NAME)
                .couponTypeCount(COUPON_TYPE_COUNT)
                .couponTypeDiscount(COUPON_TYPE_DISCOUNT)
                .couponTypeHasCode(COUPON_TYPE_HAS_CODE_CODE_TYPE)
                .startTime(LocalDateTime.now().plusDays(2L).toString())
                .endTime(LocalDateTime.now().plusDays(3L).toString())
                .build();
        assertThrows(CouponTypeCountExcessException.class,
                () -> couponType.getTotalCountAfterGenerate(10L, 1L));
    }

    @Test
    @DisplayName("CouponType, 다운로드형 확인 실패, 코드형 쿠폰 타입")
    void successfullyCheckDownloadable() {
        CouponType couponType = CouponType.builder()
                .couponTypeName(COUPON_TYPE_NAME)
                .couponTypeCount(COUPON_TYPE_COUNT)
                .couponTypeDiscount(COUPON_TYPE_DISCOUNT)
                .couponTypeHasCode(COUPON_TYPE_HAS_CODE_CODE_TYPE)
                .startTime(LocalDateTime.now().plusDays(2L).toString())
                .endTime(LocalDateTime.now().plusDays(3L).toString())
                .build();
        assertThrows(IllegalCouponTypeException.class, couponType::checkDownloadable);
    }
}