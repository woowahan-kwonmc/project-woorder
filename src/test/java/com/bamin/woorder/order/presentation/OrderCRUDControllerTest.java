package com.bamin.woorder.order.presentation;

import com.bamin.woorder.AcceptanceTestUtils;
import com.bamin.woorder.common.utils.easyrestassured.EasyExpectBodies;
import com.bamin.woorder.common.utils.easyrestassured.EasyRestAssured;
import com.bamin.woorder.common.utils.easyrestassured.EasyRestAssuredRequestMethod;
import com.bamin.woorder.order.dto.OrderCreateData;
import com.bamin.woorder.order.dto.OrderCreateRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static com.bamin.woorder.common.dto.ResponseDtoStatusCode.BAD_REQUEST;
import static com.bamin.woorder.common.dto.ResponseDtoStatusCode.OK;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

class OrderCRUDControllerTest extends AcceptanceTestUtils {

    @Test
    @DisplayName("[POST] /api/v1/orders, 주문 데이터 생성 성공")
    void successfullyCreateOrders() {
        OrderCreateRequestDto requestDto = new OrderCreateRequestDto(
                Arrays.asList(
                        new OrderCreateData(1L, 1L, 1),
                        new OrderCreateData(2L, 1L, 3)
                )
        );

        EasyRestAssured
                .givenBody(requestDto)
                .whenRequest(baseUrl("/api/v1/orders"), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(
                        OK, "/api/v1/orders",
                        EasyExpectBodies.builder()
                                .insert("message", is("주문 성공"))
                                .insert("data.orders.size()", is(2))
                                .insert("data.orders[0]", is(notNullValue()))
                                .insert("data.orders[0].quantity", is(1))
                                .insert("data.orders[1]", is(notNullValue()))
                                .insert("data.orders[1].quantity", is(3))
                );
    }

    @Test
    @DisplayName("[POST] /api/v1/orders, 주문 데이터 생성 실패, 비어 있는 주문 리스")
    void failedCreateOrders() {
        EasyRestAssured
                .givenBody(new OrderCreateRequestDto(Collections.emptyList()))
                .whenRequest(baseUrl("/api/v1/orders"), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(
                        BAD_REQUEST, "/api/v1/orders",
                        EasyExpectBodies.builder()
                                .insert("message", is("주문은 비어있을 수 없습니다."))
                );
    }
}