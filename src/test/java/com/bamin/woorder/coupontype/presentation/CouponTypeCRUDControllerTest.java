package com.bamin.woorder.coupontype.presentation;

import com.bamin.woorder.AcceptanceTestUtils;
import com.bamin.woorder.common.utils.easyrestassured.EasyExpectBodies;
import com.bamin.woorder.common.utils.easyrestassured.EasyGivenQueryParameters;
import com.bamin.woorder.common.utils.easyrestassured.EasyRestAssured;
import com.bamin.woorder.common.utils.easyrestassured.EasyRestAssuredRequestMethod;
import com.bamin.woorder.coupontype.dto.CouponTypeCreateRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.bamin.woorder.common.dto.ResponseDtoStatusCode.BAD_REQUEST;
import static com.bamin.woorder.common.dto.ResponseDtoStatusCode.OK;
import static com.bamin.woorder.coupontype.presentation.CouponTypeConstants.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

class CouponTypeCRUDControllerTest extends AcceptanceTestUtils {

    @Test
    @DisplayName("[POST] /api/v1/couponTypes, 쿠폰 타입 생성 성공")
    void successfullyCreateCouponType() {
        String startTime = LocalDateTime.now().plusDays(2L).withNano(0).toString();
        String endTime = LocalDateTime.now().plusDays(3L).withNano(0).toString();
        EasyRestAssured
                .givenBody(new CouponTypeCreateRequestDto(
                        CREATE_COUPON_TYPE_NAME,
                        CREATE_COUPON_TYPE_COUNT,
                        CREATE_COUPON_TYPE_DISCOUNT,
                        startTime,
                        endTime))
                .whenRequest(baseUrl(COUPON_TYPE_SERVLET_BASE_PATH), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(OK, COUPON_TYPE_SERVLET_BASE_PATH,
                        EasyExpectBodies.builder()
                                .insert("message", is(COUPON_TYPE_CREATE_SUCCEED_RESPONSE_MESSAGE))
                                .insert("data.couponType.no", is(notNullValue()))
                                .insert("data.couponType.name", is(CREATE_COUPON_TYPE_NAME))
                                .insert("data.couponType.count", is(CREATE_COUPON_TYPE_COUNT_INT))
                                .insert("data.couponType.discount", is(CREATE_COUPON_TYPE_DISCOUNT_INT))
                                .insert("data.couponType.startTime", is(startTime))
                                .insert("data.couponType.endTime", is(endTime))
                                .build());
    }

    @Test
    @DisplayName("[POST] /api/v1/couponTypes, 쿠폰 타입 생성 실패, 기간 설정 오류, 현재보다 이른 시작 시간")
    void failedCreateCouponTypeStartTimeIsEarlierThanCurrentTime() {
        String startTime = LocalDateTime.now().minusDays(3L).withNano(0).toString();
        String endTime = LocalDateTime.now().plusDays(3L).withNano(0).toString();
        EasyRestAssured
                .givenBody(new CouponTypeCreateRequestDto(
                        CREATE_COUPON_TYPE_NAME,
                        CREATE_COUPON_TYPE_COUNT,
                        CREATE_COUPON_TYPE_DISCOUNT,
                        startTime,
                        endTime))
                .whenRequest(baseUrl(COUPON_TYPE_SERVLET_BASE_PATH), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(BAD_REQUEST, COUPON_TYPE_SERVLET_BASE_PATH,
                        EasyExpectBodies.builder()
                                .insert("message", is(COUPON_TYPE_PERIOD_FAILED_RESPONSE_MESSAGE))
                                .insert("data.currentTime", is(notNullValue()))
                                .insert("data.startTime", is(startTime))
                                .insert("data.endTime", is(endTime))
                                .build());
    }

    @Test
    @DisplayName("[POST] /api/v1/couponTypes, 쿠폰 타입 생성 실패, 기간 설정 오류, 현재보다 이른 종료 시간")
    void failedCreateCouponTypeEndTimeIsEarlierThan() {
        String startTime = LocalDateTime.now().minusDays(3L).withNano(0).toString();
        String endTime = LocalDateTime.now().minusDays(3L).withNano(0).toString();
        EasyRestAssured
                .givenBody(new CouponTypeCreateRequestDto(
                        CREATE_COUPON_TYPE_NAME,
                        CREATE_COUPON_TYPE_COUNT,
                        CREATE_COUPON_TYPE_DISCOUNT,
                        startTime,
                        endTime))
                .whenRequest(baseUrl(COUPON_TYPE_SERVLET_BASE_PATH), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(BAD_REQUEST, COUPON_TYPE_SERVLET_BASE_PATH,
                        EasyExpectBodies.builder()
                                .insert("message", is(COUPON_TYPE_PERIOD_FAILED_RESPONSE_MESSAGE))
                                .insert("data.currentTime", is(notNullValue()))
                                .insert("data.startTime", is(startTime))
                                .insert("data.endTime", is(endTime))
                                .build());
    }

    @Test
    @DisplayName("[POST] /api/v1/couponTypes, 쿠폰 타입 생성 실패, 기간 설정 오류, 시작 시간과 종료 시간 동일")
    void failedCreateCouponTypeSameStartAndEnd() {
        String sameTime = LocalDateTime.now().plusDays(3L).withNano(0).toString();
        EasyRestAssured
                .givenBody(new CouponTypeCreateRequestDto(
                        CREATE_COUPON_TYPE_NAME,
                        CREATE_COUPON_TYPE_COUNT,
                        CREATE_COUPON_TYPE_DISCOUNT,
                        sameTime,
                        sameTime))
                .whenRequest(baseUrl(COUPON_TYPE_SERVLET_BASE_PATH), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(BAD_REQUEST, COUPON_TYPE_SERVLET_BASE_PATH,
                        EasyExpectBodies.builder()
                                .insert("message", is(COUPON_TYPE_PERIOD_FAILED_RESPONSE_MESSAGE))
                                .insert("data.currentTime", is(notNullValue()))
                                .insert("data.startTime", is(sameTime))
                                .insert("data.endTime", is(sameTime))
                                .build());
    }

    @Test
    @DisplayName("[POST] /api/v1/couponTypes, 쿠폰 타입 생성 실패, 기간 설정 오류, 기간이 현재 보다 빠름")
    void failedCreateCouponTypePeriodIsEarlierThanNow() {
        String startTime = LocalDateTime.now().minusDays(3L).withNano(0).toString();
        String endTime = LocalDateTime.now().minusDays(1L).withNano(0).toString();
        EasyRestAssured
                .givenBody(new CouponTypeCreateRequestDto(
                        CREATE_COUPON_TYPE_NAME,
                        CREATE_COUPON_TYPE_COUNT,
                        CREATE_COUPON_TYPE_DISCOUNT,
                        startTime,
                        endTime))
                .whenRequest(baseUrl(COUPON_TYPE_SERVLET_BASE_PATH), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(BAD_REQUEST, COUPON_TYPE_SERVLET_BASE_PATH,
                        EasyExpectBodies.builder()
                                .insert("message", is(COUPON_TYPE_PERIOD_FAILED_RESPONSE_MESSAGE))
                                .insert("data.currentTime", is(notNullValue()))
                                .insert("data.startTime", is(startTime))
                                .insert("data.endTime", is(endTime))
                                .build());
    }

    @Test
    @DisplayName("[GET] /api/v1/couponTypes/all, 쿠폰 타입 리스트 조회 성공")
    void successfullySelectPageCouponTypes() {
        EasyRestAssured
                .givenParams(new EasyGivenQueryParameters()
                        .addParam("page", 1)
                        .addParam("num", 3))
                .whenRequest(baseUrl(COUPON_TYPE_ALL_SERVLET_PATH), EasyRestAssuredRequestMethod.GET)
                .thenExpectDescriptiveWith(OK, COUPON_TYPE_ALL_SERVLET_PATH,
                        EasyExpectBodies.builder()
                                .insert("message", is(String.format(COUPON_TYPES_SELECT_SUCCEED_RESPONSE_MESSAGE, 1, 3)))
                                .insert("data.couponTypes.size()", is(3)));
    }
}