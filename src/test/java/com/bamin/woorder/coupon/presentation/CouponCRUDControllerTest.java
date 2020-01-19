package com.bamin.woorder.coupon.presentation;

import com.bamin.woorder.AcceptanceTestUtils;
import com.bamin.woorder.common.utils.easyrestassured.EasyExpectBodies;
import com.bamin.woorder.common.utils.easyrestassured.EasyGivenPathVariables;
import com.bamin.woorder.common.utils.easyrestassured.EasyGivenQueryParameters;
import com.bamin.woorder.coupon.domain.CouponStatus;
import com.bamin.woorder.coupon.dto.CouponCreateRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import static com.bamin.woorder.common.dto.ResponseDtoStatusCode.*;
import static com.bamin.woorder.common.utils.easyrestassured.EasyRestAssured.*;
import static com.bamin.woorder.common.utils.easyrestassured.EasyRestAssuredRequestMethod.GET;
import static com.bamin.woorder.common.utils.easyrestassured.EasyRestAssuredRequestMethod.POST;
import static com.bamin.woorder.coupon.CouponConstants.*;
import static org.hamcrest.CoreMatchers.*;

class CouponCRUDControllerTest extends AcceptanceTestUtils {

    @Test
    @DirtiesContext
    @DisplayName("[POST] /api/v1/coupons/downloadMode, 다운로드 쿠폰 1개 생성 성공")
    void successfullyCreateDownloadableCoupon() {
        givenBody(new CouponCreateRequestDto(DOWNLOAD_COUPON_TYPE_NO, 1L))
                .whenRequest(baseUrl(CREATE_COUPON_DOWNLOAD_MODE_SERVLET_PATH), POST)
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
        givenBody(new CouponCreateRequestDto(DOWNLOAD_COUPON_TYPE_NO, 5L))
                .whenRequest(baseUrl(CREATE_COUPON_DOWNLOAD_MODE_SERVLET_PATH), POST)
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
        givenBody(new CouponCreateRequestDto(DOWNLOAD_COUPON_TYPE_NO, 1_000_000_000L))
                .whenRequest(baseUrl(CREATE_COUPON_DOWNLOAD_MODE_SERVLET_PATH), POST)
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
        givenBody(new CouponCreateRequestDto(CODE_COUPON_TYPE_NO, 1L))
                .whenRequest(baseUrl(CREATE_COUPON_DOWNLOAD_MODE_SERVLET_PATH), POST)
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
        givenBody(new CouponCreateRequestDto(PAST_PERIOD_COUPON_TYPE_NO, 1L))
                .whenRequest(baseUrl(CREATE_COUPON_DOWNLOAD_MODE_SERVLET_PATH), POST)
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
        givenBody(new CouponCreateRequestDto(CODE_COUPON_TYPE_NO, 10L))
                .whenRequest(baseUrl(CREATE_COUPON_CODE_MODE_SERVLET_PATH), POST)
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
                                .insert("data.couponType.name", is(CODE_COUPON_TYPE_NAME))
                                .insert("data.couponType.hasCode", is(true))
                );
    }

    @Test
    @DisplayName("[GET] /api/v1/coupons, 조건에 관계 없이 모든 쿠폰 조회")
    void successfullySelectAllPageCoupons() {
        givenParams(new EasyGivenQueryParameters()
                .addParam("page", 1L)
                .addParam("num", 6L))
                .whenRequest(baseUrl("/api/v1/coupons"), GET)
                .thenExpectDescriptiveWith(OK, "/api/v1/coupons",
                        EasyExpectBodies.builder()
                                .insert("message", is("쿠폰 목록 조회"))
                                .insert("data.coupons.size()", is(6))
                                .insert("data.request.page", is(1))
                                .insert("data.request.num", is(6))
                                .insert("data.request.usable", is(false))
                                .insert("data.request.expired", is(false))
                );
    }

    @Test
    @DisplayName("[GET] /api/v1/coupons, 사용된 쿠폰 중 만료된 모든 쿠폰 조회")
    void selectAllPageUsedCoupons() {
        givenParams(new EasyGivenQueryParameters()
                .addParam("page", 1L)
                .addParam("num", 3L)
                .addParam("usable", "false")
                .addParam("expired", "true"))
                .whenRequest(baseUrl("/api/v1/coupons"), GET)
                .thenExpectDescriptiveWith(OK, "/api/v1/coupons",
                        EasyExpectBodies.builder()
                                .insert("message", is("쿠폰 목록 조회"))
                                .insert("data.coupons.size()", is(3))
                                .insert("data.request.page", is(1))
                                .insert("data.request.num", is(3))
                                .insert("data.request.usable", is(false))
                                .insert("data.request.expired", is(true))
                );
    }

    @Test
    @DisplayName("[GET] /api/v1/coupons, expired 된 모든 쿠폰 조회")
    void successfullySelectAllPageExpiredCoupons() {
        givenParams(new EasyGivenQueryParameters()
                .addParam("page", 1L)
                .addParam("num", 3L)
                .addParam("expired", "true"))
                .whenRequest(baseUrl("/api/v1/coupons"), GET)
                .thenExpectDescriptiveWith(OK, "/api/v1/coupons",
                        EasyExpectBodies.builder()
                                .insert("message", is("쿠폰 목록 조회"))
                                .insert("data.coupons.size()", is(3))
                                .insert("data.request.page", is(1))
                                .insert("data.request.num", is(3))
                                .insert("data.request.usable", is(false))
                                .insert("data.request.expired", is(true))
                );
    }

    @Test
    @DisplayName("[GET] /api/v1/coupons, usable 하고 expired 되지 않은 모든쿠폰 조회")
    void successfullySelectAllPageUsableCoupons() {
        givenParams(new EasyGivenQueryParameters()
                .addParam("page", 1L)
                .addParam("num", 3L)
                .addParam("expired", "false")
                .addParam("usable", "true"))
                .whenRequest(baseUrl("/api/v1/coupons"), GET)
                .thenExpectDescriptiveWith(OK, "/api/v1/coupons",
                        EasyExpectBodies.builder()
                                .insert("message", is("쿠폰 목록 조회"))
                                .insert("data.coupons.size()", is(2))
                                .insert("data.request.page", is(1))
                                .insert("data.request.num", is(3))
                                .insert("data.request.usable", is(true))
                                .insert("data.request.expired", is(false))
                );
    }

    @Test
    @DisplayName("[GET] /api/v1/coupons/{couponNo}, 쿠폰 No 로 쿠폰 조회")
    void successfullyFindCouponByCouponNo() {
        EasyGivenPathVariables pathVariables = new EasyGivenPathVariables()
                .addVariables("couponNo", 1);

        givenPathVariable(pathVariables)
                .whenRequest(baseUrl("/api/v1/coupons/{couponNo}"), GET)
                .thenExpectDescriptiveWith(OK, "/api/v1/coupons/1",
                        EasyExpectBodies.builder()
                                .insert("message", is("쿠폰 넘버로 조회"))
                                .insert("data.coupon.code", is("ABCDEF123456"))
                                .insert("data.coupon.useStatus", is("USABLE"))
                                .insert("data.coupon.name", is("봉대표가 쏜다"))
                                .insert("data.coupon.discount", is(200000))
                                .insert("data.coupon.startTime", is(notNullValue()))
                                .insert("data.coupon.endTime", is(notNullValue()))
                );
    }

    @Test
    @DisplayName("[GET] /api/v1/coupons/{couponNo}, 쿠폰 No 로 쿠폰 조회 실패, 존재하지 않음")
    void failedFindCouponByCouponNo() {
        EasyGivenPathVariables pathVariables = new EasyGivenPathVariables()
                .addVariables("couponNo", 1_000_000_000);

        givenPathVariable(pathVariables)
                .whenRequest(baseUrl("/api/v1/coupons/{couponNo}"), GET)
                .thenExpectDescriptiveWith(NOT_FOUND, "/api/v1/coupons/1000000000",
                        EasyExpectBodies.builder()
                                .insert("message", is("해당하는 쿠폰이 존재하지 않습니다."))
                                .insert("data.requestNo", is(1000000000))
                );
    }

    @Test
    @DisplayName("[GET] /api/v1/coupons/code/{couponCode}, 쿠폰 Code 로 쿠폰 조회")
    void successfullyFindCouponByCouponCode() {
        EasyGivenPathVariables pathVariables = new EasyGivenPathVariables()
                .addVariables("couponCode", "ABCDEF123456");

        givenPathVariable(pathVariables)
                .whenRequest(baseUrl("/api/v1/coupons/code/{couponCode}"), GET)
                .thenExpectDescriptiveWith(OK, "/api/v1/coupons/code/ABCDEF123456",
                        EasyExpectBodies.builder()
                                .insert("message", is("쿠폰 코드로 조회"))
                                .insert("data.coupon.code", is("ABCDEF123456"))
                                .insert("data.coupon.useStatus", is("USABLE"))
                                .insert("data.coupon.name", is("봉대표가 쏜다"))
                                .insert("data.coupon.discount", is(200000))
                                .insert("data.coupon.startTime", is(notNullValue()))
                                .insert("data.coupon.endTime", is(notNullValue()))
                );
    }

    @Test
    @DisplayName("[GET] /api/v1/coupons/code/{couponCode}, 쿠폰 Code 로 쿠폰 조회 실패, 존재하지 않음")
    void failedFindCouponByCouponCodeNotFound() {
        EasyGivenPathVariables pathVariables = new EasyGivenPathVariables()
                .addVariables("couponCode", "TTTTTTTTTTTT");

        givenPathVariable(pathVariables)
                .whenRequest(baseUrl("/api/v1/coupons/code/{couponCode}"), GET)
                .thenExpectDescriptiveWith(NOT_FOUND, "/api/v1/coupons/code/TTTTTTTTTTTT",
                        EasyExpectBodies.builder()
                                .insert("message", is("해당하는 쿠폰이 존재하지 않습니다."))
                                .insert("data.requestCode", is("TTTTTTTTTTTT"))
                );
    }
}