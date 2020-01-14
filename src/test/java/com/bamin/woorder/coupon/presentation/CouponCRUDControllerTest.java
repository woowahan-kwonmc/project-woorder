package com.bamin.woorder.coupon.presentation;

import com.bamin.woorder.AcceptanceTestUtils;
import com.bamin.woorder.common.utils.easyrestassured.EasyExpectBodies;
import com.bamin.woorder.common.utils.easyrestassured.EasyRestAssured;
import com.bamin.woorder.common.utils.easyrestassured.EasyRestAssuredRequestMethod;
import com.bamin.woorder.coupon.domain.CouponStatus;
import com.bamin.woorder.coupon.dto.CouponCreateRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import static com.bamin.woorder.common.dto.ResponseDtoStatusCode.BAD_REQUEST;
import static com.bamin.woorder.common.dto.ResponseDtoStatusCode.OK;
import static com.bamin.woorder.coupon.CouponConstants.*;
import static org.hamcrest.CoreMatchers.*;

class CouponCRUDControllerTest extends AcceptanceTestUtils {

    @Test
    @DirtiesContext
    @DisplayName("[POST] /api/v1/coupons/downloadMode, 다운로드 쿠폰 1개 생성 성공")
    void successfullyCreateDownloadableCoupon() {
        EasyRestAssured
                .givenBody(new CouponCreateRequestDto(DOWNLOAD_COUPON_TYPE_NO, 1L))
                .whenRequest(baseUrl(CREATE_COUPON_DOWNLOAD_MODE_SERVLET_PATH), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(OK, CREATE_COUPON_DOWNLOAD_MODE_SERVLET_PATH,
                        EasyExpectBodies.builder()
                                .insert("message", is(CREATE_COUPON_SUCCEED_RESPONSE_MESSAGE))
                                .insert("data.coupons.size()", is(1))
                                .insert("data.coupons[0].code", is(nullValue()))
                                .insert("data.coupons[0].status", is(CouponStatus.USABLE.name()))
                                .insert("data.couponType.name", is("치킨 복날 이벤트"))
                                .insert("data.couponType.discount", is(3_000))
                                .insert("data.couponType.currentCount", is(1))
                                .insert("data.couponType.totalCount", is(10_000))
                                .insert("data.couponType.hasCode", is(false))
                );
    }

    @Test
    @DirtiesContext
    @DisplayName("[POST] /api/v1/coupons/downloadMode, 다운로드 쿠폰 여러 개 생성 성공")
    void successfullyCreateDownloadableCoupons() {
        EasyRestAssured
                .givenBody(new CouponCreateRequestDto(DOWNLOAD_COUPON_TYPE_NO, 5L))
                .whenRequest(baseUrl(CREATE_COUPON_DOWNLOAD_MODE_SERVLET_PATH), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(OK, CREATE_COUPON_DOWNLOAD_MODE_SERVLET_PATH,
                        EasyExpectBodies.builder()
                                .insert("message", is(CREATE_COUPON_SUCCEED_RESPONSE_MESSAGE))
                                .insert("data.coupons.size()", is(5))
                                .insert("data.coupons[0]", is(notNullValue()))
                                .insert("data.coupons[1]", is(notNullValue()))
                                .insert("data.coupons[2]", is(notNullValue()))
                                .insert("data.coupons[3]", is(notNullValue()))
                                .insert("data.coupons[4]", is(notNullValue()))
                                .insert("data.couponType.name", is("치킨 복날 이벤트"))
                                .insert("data.couponType.discount", is(3_000))
                                .insert("data.couponType.currentCount", is(5))
                                .insert("data.couponType.totalCount", is(10_000))
                                .insert("data.couponType.hasCode", is(false))
                );
    }

    @Test
    @DisplayName("[POST] /api/v1/coupons/downloadMode, 다운로드 쿠폰 여러 개 생성 실패, 생성 가능 개수 초과")
    void failedCreateDownloadableCouponsExcessLimitCounts() {
        EasyRestAssured
                .givenBody(new CouponCreateRequestDto(DOWNLOAD_COUPON_TYPE_NO, 1_000_000_000L))
                .whenRequest(baseUrl(CREATE_COUPON_DOWNLOAD_MODE_SERVLET_PATH), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(BAD_REQUEST, CREATE_COUPON_DOWNLOAD_MODE_SERVLET_PATH,
                        EasyExpectBodies.builder()
                                .insert("message", is(CREATE_COUPON_FAILED_EXCESS_RESPONSE_MESSAGE))
                                .insert("data.currentCount", is(0))
                                .insert("data.requestCount", is(1000000000))
                                .insert("data.limitCount", is(10000))
                );
    }

    @Test
    @DisplayName("[POST] /api/v1/coupons/downloadMode, 쿠폰 생성 실패, 쿠폰 모드 불일치")
    void failedCreateCouponNotMatchCouponTypeMode() {
        EasyRestAssured
                .givenBody(new CouponCreateRequestDto(CODE_COUPON_TYPE_NO, 1L))
                .whenRequest(baseUrl(CREATE_COUPON_DOWNLOAD_MODE_SERVLET_PATH), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(BAD_REQUEST, CREATE_COUPON_DOWNLOAD_MODE_SERVLET_PATH,
                        EasyExpectBodies.builder()
                                .insert("message", is(CREATE_COUPON_FAILED_CODE_NEEDED_RESPONSE_MESSAGE))
                                .insert("data.couponNo", is(1))
                                .insert("data.couponName", is("이달의 카피라이터"))
                                .insert("data.couponHasCode", is(true))
                );
    }

    @Test
    @DisplayName("[POST] /api/v1/coupons/*, 쿠폰 생성 실패, 기간 지남")
    void failedCreateCouponAfterPeriod() {
        EasyRestAssured
                .givenBody(new CouponCreateRequestDto(PAST_PERIOD_COUPON_TYPE_NO, 1L))
                .whenRequest(baseUrl(CREATE_COUPON_DOWNLOAD_MODE_SERVLET_PATH), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(BAD_REQUEST, CREATE_COUPON_DOWNLOAD_MODE_SERVLET_PATH,
                        EasyExpectBodies.builder()
                                .insert("message", is(CREATE_COUPON_FAILED_CREATABLE_PERIOD_RESPONSE_MESSAGE))
                                .insert("data.endTime", is("2020-01-11T15:13:20"))
                );
    }

    @Test
    @DirtiesContext
    @DisplayName("[POST] /api/v1/coupons/codeCoupon, 코드 쿠폰 생성 성공")
    void successfullyCreateCodeCoupon() {
        EasyRestAssured
                .givenBody(new CouponCreateRequestDto(CODE_COUPON_TYPE_NO, 10L))
                .whenRequest(baseUrl(CREATE_COUPON_CODE_MODE_SERVLET_PATH), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(OK, CREATE_COUPON_CODE_MODE_SERVLET_PATH,
                        EasyExpectBodies.builder()
                                .insert("message", is(CREATE_CODE_COUPON_SUCCEED_RESPONSE_MESSAGE))
                                .insert("data.coupons.size()", is(10))
                                .insert("data.coupons[0].code", is(notNullValue()))
                                .insert("data.coupons[1].code", is(notNullValue()))
                                .insert("data.coupons[2].code", is(notNullValue()))
                                .insert("data.coupons[3].code", is(notNullValue()))
                                .insert("data.coupons[4].code", is(notNullValue()))
                                .insert("data.coupons[5].code", is(notNullValue()))
                                .insert("data.coupons[6].code", is(notNullValue()))
                                .insert("data.coupons[7].code", is(notNullValue()))
                                .insert("data.coupons[8].code", is(notNullValue()))
                                .insert("data.coupons[9].code", is(notNullValue()))
                                .insert("data.couponType.name", is(CODE_COUPON_TYPE_NO))
                                .insert("data.couponType.hasCode", is(true))
                );
    }
}