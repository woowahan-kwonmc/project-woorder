package com.bamin.woorder.payment.presentation;

import com.bamin.woorder.AcceptanceTestUtils;
import com.bamin.woorder.common.utils.easyrestassured.EasyExpectBodies;
import com.bamin.woorder.common.utils.easyrestassured.EasyRestAssuredRequestMethod;
import com.bamin.woorder.payment.dto.PaymentCreateRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Arrays;
import java.util.Collections;

import static com.bamin.woorder.common.dto.ResponseDtoStatusCode.*;
import static com.bamin.woorder.common.utils.easyrestassured.EasyRestAssured.givenBody;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

class PaymentCRUDControllerTest extends AcceptanceTestUtils {

    @Test
    @DirtiesContext
    @DisplayName("[POST] /api/v1/payments, 현금 결제 데이터 생성 성공")
    void successfullyCreateOrdersUsingCash() {
        PaymentCreateRequestDto requestDto = PaymentCreateRequestDto.builder()
                .method("CASH")
                .memberNo(2L)
                .ordersNo(Arrays.asList(2L, 3L))
                .couponsNo(Collections.singletonList(7L))
                .build();

        givenBody(requestDto)
                .whenRequest(baseUrl("/api/v1/payments"), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(
                        OK, "/api/v1/payments",
                        EasyExpectBodies.builder()
                                .insert("message", is("결제 정보 생성 성공"))
                                .insert("data.payment.no", is(notNullValue()))
                                .insert("data.payment.method", is("CASH"))
                                .insert("data.payment.status", is("INCOMPLETE"))
                                .insert("data.payment.price", is(18000))
                                .insert("data.payment.createTime", is(notNullValue()))
                                .insert("data.payment.modifiedTime", is(notNullValue()))
                );
    }

    @Test
    @DirtiesContext
    @DisplayName("[POST] /api/v1/payments, 비현금 결제 데이터 생성 성공")
    void successfullyCreateOrdersNotUsingCash() {
        PaymentCreateRequestDto requestDto = PaymentCreateRequestDto.builder()
                .method("BAEMIN_PAY")
                .memberNo(2L)
                .ordersNo(Arrays.asList(2L, 3L))
                .couponsNo(Collections.singletonList(7L))
                .build();

        givenBody(requestDto)
                .whenRequest(baseUrl("/api/v1/payments"), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(
                        OK, "/api/v1/payments",
                        EasyExpectBodies.builder()
                                .insert("message", is("결제 정보 생성 성공"))
                                .insert("data.payment.no", is(notNullValue()))
                                .insert("data.payment.method", is("BAEMIN_PAY"))
                                .insert("data.payment.status", is("COMPLETE"))
                                .insert("data.payment.price", is(18000))
                                .insert("data.payment.createTime", is(notNullValue()))
                                .insert("data.payment.modifiedTime", is(notNullValue()))
                );
    }

    @Test
    @DisplayName("[POST] /api/v1/payments, 결제 데이터 생성 실패, 다른 사람의 쿠폰 이용")
    void failedCreateOrdersNotOwnCoupon() {
        PaymentCreateRequestDto requestDto = PaymentCreateRequestDto.builder()
                .method("BAEMIN_PAY")
                .memberNo(2L)
                .ordersNo(Arrays.asList(2L, 3L))
                .couponsNo(Collections.singletonList(8L))
                .build();

        givenBody(requestDto)
                .whenRequest(baseUrl("/api/v1/payments"), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(
                        BAD_REQUEST, "/api/v1/payments",
                        EasyExpectBodies.builder()
                                .insert("message", is("자신이 소유한 쿠폰만 사용할 수 있습니다."))
                );
    }

    @Test
    @DisplayName("[POST] /api/v1/payments, 결제 데이터 생성 실패, 주문 목록이 비어있음")
    void failedCreateOrdersNoOrders() {
        PaymentCreateRequestDto requestDto = PaymentCreateRequestDto.builder()
                .method("BAEMIN_PAY")
                .memberNo(2L)
                .ordersNo(Collections.emptyList())
                .couponsNo(Collections.singletonList(7L))
                .build();

        givenBody(requestDto)
                .whenRequest(baseUrl("/api/v1/payments"), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(
                        BAD_REQUEST, "/api/v1/payments",
                        EasyExpectBodies.builder()
                                .insert("message", is("주문은 비어있을 수 없습니다."))
                );
    }

    @Test
    @DisplayName("[POST] /api/v1/payments, 결제 데이터 생성 실패, 할인액이 더 큼")
    void failedCreateOrdersDiscountAmountException() {
        PaymentCreateRequestDto requestDto = PaymentCreateRequestDto.builder()
                .method("BAEMIN_PAY")
                .memberNo(6L)
                .ordersNo(Collections.singletonList(14L))
                .couponsNo(Collections.singletonList(9L))
                .build();

        givenBody(requestDto)
                .whenRequest(baseUrl("/api/v1/payments"), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(
                        BAD_REQUEST, "/api/v1/payments",
                        EasyExpectBodies.builder()
                                .insert("message", is("할인 금액이 주문 금액보다 높을 수 없습니다."))
                );
    }

    @Test
    @DisplayName("[POST] /api/v1/payments, 결제 데이터 생성 실패, 존재하지 않는 결제 수단")
    void name() {
        PaymentCreateRequestDto requestDto = PaymentCreateRequestDto.builder()
                .method("T-MONEY")
                .memberNo(6L)
                .ordersNo(Collections.singletonList(14L))
                .couponsNo(Collections.singletonList(9L))
                .build();

        givenBody(requestDto)
                .whenRequest(baseUrl("/api/v1/payments"), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(
                        NOT_FOUND, "/api/v1/payments",
                        EasyExpectBodies.builder()
                                .insert("message", is("존재하지 않는 결제 수단입니다."))
                );
    }
}