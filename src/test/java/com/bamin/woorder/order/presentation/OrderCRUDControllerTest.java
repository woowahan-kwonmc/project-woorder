package com.bamin.woorder.order.presentation;

import com.bamin.woorder.AcceptanceTestUtils;
import com.bamin.woorder.common.utils.easyrestassured.EasyExpectBodies;
import com.bamin.woorder.common.utils.easyrestassured.EasyGivenPathVariables;
import com.bamin.woorder.common.utils.easyrestassured.EasyGivenQueryParameters;
import com.bamin.woorder.common.utils.easyrestassured.EasyRestAssuredRequestMethod;
import com.bamin.woorder.order.dto.OrderCreateData;
import com.bamin.woorder.order.dto.OrderCreateRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static com.bamin.woorder.common.dto.ResponseDtoStatusCode.*;
import static com.bamin.woorder.common.utils.easyrestassured.EasyRestAssured.*;
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

        givenBody(requestDto)
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
    @DisplayName("[POST] /api/v1/orders, 주문 데이터 생성 실패, 비어 있는 주문 리스트")
    void failedCreateOrders() {
        givenBody(new OrderCreateRequestDto(Collections.emptyList()))
                .whenRequest(baseUrl("/api/v1/orders"), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(
                        BAD_REQUEST, "/api/v1/orders",
                        EasyExpectBodies.builder()
                                .insert("message", is("주문은 비어있을 수 없습니다."))
                );
    }

    @Test
    @DisplayName("[GET] /api/v1/orders/{orderNo}, 주문 조회 성공")
    void successfullySelectOrder() {
        EasyGivenPathVariables pathVariables = new EasyGivenPathVariables().addVariables("orderNo", 1);

        givenPathVariable(pathVariables)
                .whenRequest(baseUrl("/api/v1/orders/{orderNo}"), EasyRestAssuredRequestMethod.GET)
                .thenExpectDescriptiveWith(
                        OK, String.format("/api/v1/orders/%d", 1),
                        EasyExpectBodies.builder()
                                .insert("message", is("주문 조회 성공"))
                                .insert("data.order.no", is(1))
                                .insert("data.order.status", is("CREATE"))
                                .insert("data.order.price", is(54000))
                                .insert("data.order.quantity", is(3))
                                .insert("data.order.createTime", is(notNullValue()))
                                .insert("data.order.orderedBy", is(1))
                                .insert("data.order.modifiedTime", is(notNullValue()))
                );
    }

    @Test
    @DisplayName("[GET] /api/v1/orders/{orderNo}, 주문 조회 실패, 존재하지 않음")
    void failedSelectOrderNotFound() {
        EasyGivenPathVariables pathVariables = new EasyGivenPathVariables().addVariables("orderNo", 1_000_000);

        givenPathVariable(pathVariables)
                .whenRequest(baseUrl("/api/v1/orders/{orderNo}"), EasyRestAssuredRequestMethod.GET)
                .thenExpectDescriptiveWith(
                        NOT_FOUND, String.format("/api/v1/orders/%d", 1_000_000),
                        EasyExpectBodies.builder()
                                .insert("message", is("해당하는 주문이 존재하지 않습니다."))
                                .insert("data.requestNo", is(1_000_000))
                );
    }

    @Test
    @DisplayName("[GET] /api/v1/orders, 주문자, 상태 조건 없이 조회 성공")
    void successfullyFindConditionalOrdersWithoutMemberAndStatus() {
        EasyGivenQueryParameters params = new EasyGivenQueryParameters()
                .addParam("num", 3)
                .addParam("page", 1);

        givenParams(params)
                .whenRequest(baseUrl("/api/v1/orders"), EasyRestAssuredRequestMethod.GET)
                .thenExpectDescriptiveWith(
                        OK, "/api/v1/orders",
                        EasyExpectBodies.builder()
                                .insert("message", is("주문 조건 조회 성공"))
                                .insert("data.orders.size()", is(3))
                                .insert("data.orders[0]", is(notNullValue()))
                                .insert("data.orders[1]", is(notNullValue()))
                                .insert("data.orders[2]", is(notNullValue()))
                );
    }

    @Test
    @DisplayName("[GET] /api/v1/orders, 주문자 조건 없이 조회 성공")
    void successfullyFindConditionalOrdersWithoutMember() {
        EasyGivenQueryParameters params = new EasyGivenQueryParameters()
                .addParam("num", 3)
                .addParam("page", 1)
                .addParam("status", "CANCELED");

        givenParams(params)
                .whenRequest(baseUrl("/api/v1/orders"), EasyRestAssuredRequestMethod.GET)
                .thenExpectDescriptiveWith(
                        OK, "/api/v1/orders",
                        EasyExpectBodies.builder()
                                .insert("message", is("주문 조건 조회 성공"))
                                .insert("data.orders.size()", is(3))
                                .insert("data.orders[0].status", is("CANCELED"))
                                .insert("data.orders[1].status", is("CANCELED"))
                                .insert("data.orders[2].status", is("CANCELED"))
                );
    }

    @Test
    @DisplayName("[GET] /api/v1/orders, 상태 조건 없이 조회 성공")
    void successfullyFindConditionalOrdersWithoutStatus() {
        EasyGivenQueryParameters params = new EasyGivenQueryParameters()
                .addParam("num", 3)
                .addParam("page", 1)
                .addParam("orderedBy", 4);

        givenParams(params)
                .whenRequest(baseUrl("/api/v1/orders"), EasyRestAssuredRequestMethod.GET)
                .thenExpectDescriptiveWith(
                        OK, "/api/v1/orders",
                        EasyExpectBodies.builder()
                                .insert("message", is("주문 조건 조회 성공"))
                                .insert("data.orders.size()", is(2))
                                .insert("data.orders[0].orderedBy", is(4))
                                .insert("data.orders[1].orderedBy", is(4))
                );
    }

    @Test
    @DisplayName("[GET] /api/v1/orders, 조회 성공")
    void successfullyFindConditionalOrders() {
        EasyGivenQueryParameters params = new EasyGivenQueryParameters()
                .addParam("num", 3)
                .addParam("page", 1)
                .addParam("orderedBy", 2)
                .addParam("status", "CREATE");

        givenParams(params)
                .whenRequest(baseUrl("/api/v1/orders"), EasyRestAssuredRequestMethod.GET)
                .thenExpectDescriptiveWith(
                        OK, "/api/v1/orders",
                        EasyExpectBodies.builder()
                                .insert("message", is("주문 조건 조회 성공"))
                                .insert("data.orders.size()", is(2))
                                .insert("data.orders[0].orderedBy", is(2))
                                .insert("data.orders[0].status", is("CREATE"))
                                .insert("data.orders[1].orderedBy", is(2))
                                .insert("data.orders[1].status", is("CREATE"))
                );
    }
}