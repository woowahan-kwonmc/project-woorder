package com.bamin.woorder.coupontype.domain;

import com.bamin.woorder.coupontype.domain.exception.CouponTypeCountRangeException;
import com.bamin.woorder.coupontype.domain.exception.CouponTypeDiscountAmountException;
import com.bamin.woorder.coupontype.domain.exception.CouponTypeNameLengthException;
import com.bamin.woorder.coupontype.domain.exception.CouponTypeUsablePeriodException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CouponTypeTest {

    @Test
    @DisplayName("CouponType 생성 성공")
    void successfullyConstruct() {
        assertDoesNotThrow(() -> new CouponType(
                "민철이가 쏜다.",
                "1000",
                "10",
                true,
                LocalDateTime.now().plusDays(2L).toString(),
                LocalDateTime.now().plusDays(3L).toString()));
    }

    @Test
    @DisplayName("CouponType 생성 실패, 이름")
    void failedConstructWithName() {
        assertThrows(CouponTypeNameLengthException.class, () -> new CouponType(
                "",
                "1000",
                "10",
                true,
                LocalDateTime.now().plusDays(2L).toString(),
                LocalDateTime.now().plusDays(3L).toString()));
    }

    @Test
    @DisplayName("CouponType 생성 실패, 수량")
    void failedConstructWithCount() {
        assertThrows(CouponTypeCountRangeException.class, () -> new CouponType(
                "민철이가 쏜다.",
                "0",
                "10",
                true,
                LocalDateTime.now().plusDays(2L).toString(),
                LocalDateTime.now().plusDays(3L).toString()));
    }

    @Test
    @DisplayName("CouponType 생성 실패, 가격")
    void failedConstructWithDiscount() {
        assertThrows(CouponTypeDiscountAmountException.class, () -> new CouponType(
                "민철이가 쏜다.",
                "1",
                "0",
                true,
                LocalDateTime.now().plusDays(2L).toString(),
                LocalDateTime.now().plusDays(3L).toString()));
    }

    @Test
    @DisplayName("CouponType 생성 실패, 기간")
    void failedConstructWithPeriod() {
        assertThrows(CouponTypeUsablePeriodException.class, () -> new CouponType(
                "민철이가 쏜다.",
                "1",
                "1",
                true,
                LocalDateTime.now().minusDays(2L).toString(),
                LocalDateTime.now().plusDays(3L).toString()));
    }
}