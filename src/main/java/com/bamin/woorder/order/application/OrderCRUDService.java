package com.bamin.woorder.order.application;

import com.bamin.woorder.common.dto.ResponseData;
import com.bamin.woorder.common.dto.ResponseDto;
import com.bamin.woorder.common.dto.ResponseDtoMethod;
import com.bamin.woorder.common.dto.ResponseDtoStatusCode;
import com.bamin.woorder.member.application.MemberService;
import com.bamin.woorder.menu.application.MenuService;
import com.bamin.woorder.order.domain.Order;
import com.bamin.woorder.order.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderCRUDService {

    private OrderService orderService;
    private MenuService menuService;
    private MemberService memberService;

    @Autowired
    public OrderCRUDService(final OrderService orderService,
                            final MenuService menuService,
                            final MemberService memberService) {
        this.orderService = orderService;
        this.menuService = menuService;
        this.memberService = memberService;
    }

    public ResponseDto createOrders(final OrderCreateRequestDto requestDto) {
        List<Order> orders = mapToOrders(requestDto.getOrders());
        List<Order> createdOrders = orderService.createOrders(orders);
        return ResponseDto.builder()
                .path("/api/v1/orders")
                .method(ResponseDtoMethod.POST)
                .message("주문 성공")
                .data(ResponseData.builder()
                        .insert("orders", mapToOrderResponseData(createdOrders))
                        .build())
                .statusCode(ResponseDtoStatusCode.OK)
                .build();
    }

    public ResponseDto findOrderByOrderNo(final Long orderNo) {
        Order savedOrder = orderService.findByOrderNo(orderNo);
        return ResponseDto.builder()
                .path(String.format("/api/v1/orders/%d", orderNo))
                .method(ResponseDtoMethod.GET)
                .message("주문 조회 성공")
                .data(ResponseData.builder()
                        .insert("order", mapToOrderResponseDto(savedOrder))
                        .build())
                .statusCode(ResponseDtoStatusCode.OK)
                .build();
    }

    public ResponseDto findConditionalOrders(final OrderConditionalRequestDto requestDto) {
        List<Order> conditionalOrders = orderService.findConditionalOrders(requestDto.getNum(),
                requestDto.getPage(), requestDto.getStatus(), requestDto.getOrderedBy());
        return ResponseDto.builder()
                .path("/api/v1/orders")
                .method(ResponseDtoMethod.GET)
                .message("주문 조건 조회 성공")
                .data(ResponseData.builder()
                        .insert("orders", mapToOrderResponseDtos(conditionalOrders))
                        .build())
                .statusCode(ResponseDtoStatusCode.OK)
                .build();
    }

    public ResponseDto cancelOrder(final Long orderNo) {
        Order canceledOrder = orderService.cancelOrder(orderNo);
        return ResponseDto.builder()
                .path("/api/v1/orders")
                .method(ResponseDtoMethod.PATCH)
                .message("주문 취소 성공")
                .data(ResponseData.builder()
                        .insert("canceledOrder", mapToCancelResponseDto(canceledOrder))
                        .build())
                .statusCode(ResponseDtoStatusCode.OK)
                .build();
    }

    private OrderResponseDto mapToOrderResponseDto(final Order order) {
        return OrderResponseDto.dtoBuilder()
                .no(order.getOrderNo())
                .status(order.getStatus())
                .quantity(order.getQuantity())
                .price(order.getPrice())
                .createTime(order.getCreateTime())
                .orderedBy(order.getOrderMemberNo())
                .modifiedTime(order.getModifiedTime())
                .build();
    }

    private OrderCancelResponseDto mapToCancelResponseDto(final Order order) {
        return OrderCancelResponseDto.dtoBuilder()
                .no(order.getOrderNo())
                .status(order.getStatus())
                .quantity(order.getQuantity())
                .price(order.getPrice())
                .createTime(order.getCreateTime())
                .orderedBy(order.getOrderMemberNo())
                .deletedTime(order.getDeleteTime())
                .build();
    }

    private List<Order> mapToOrders(final List<OrderCreateData> requestOrders) {
        return requestOrders.stream()
                .map(this::mapToOrder)
                .collect(Collectors.toList());
    }

    private Order mapToOrder(final OrderCreateData orderCreateData) {
        return Order.builder()
                .quantity(orderCreateData.getQuantity())
                .savedMenu(menuService.findMenuById(orderCreateData.getMenuNo()))
                .savedMember(memberService.findMemberByNo(orderCreateData.getMemberNo()))
                .build();
    }

    private List<OrderResponseData> mapToOrderResponseData(List<Order> orders) {
        return orders.stream()
                .map(this::mapToResponseData)
                .collect(Collectors.toList());
    }

    private List<OrderResponseDto> mapToOrderResponseDtos(List<Order> orders) {
        return orders.stream()
                .map(this::mapToOrderResponseDto)
                .collect(Collectors.toList());
    }

    private OrderResponseData mapToResponseData(final Order order) {
        return OrderResponseData.builder()
                .no(order.getOrderNo())
                .status(order.getStatus())
                .price(order.getPrice())
                .quantity(order.getQuantity())
                .createTime(order.getCreateTime())
                .build();
    }
}
