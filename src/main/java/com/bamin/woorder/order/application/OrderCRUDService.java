package com.bamin.woorder.order.application;

import com.bamin.woorder.common.dto.ResponseData;
import com.bamin.woorder.common.dto.ResponseDto;
import com.bamin.woorder.common.dto.ResponseDtoMethod;
import com.bamin.woorder.common.dto.ResponseDtoStatusCode;
import com.bamin.woorder.member.application.MemberService;
import com.bamin.woorder.menu.application.MenuService;
import com.bamin.woorder.order.domain.Order;
import com.bamin.woorder.order.dto.OrderCreateData;
import com.bamin.woorder.order.dto.OrderCreateRequestDto;
import com.bamin.woorder.order.dto.OrderResponseData;
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
