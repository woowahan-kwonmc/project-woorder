package com.bamin.woorder.order.presentation;

import com.bamin.woorder.order.application.OrderCRUDService;
import com.bamin.woorder.order.dto.OrderCreateData;
import com.bamin.woorder.order.dto.OrderCreateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1")
@RestController
public class OrderCRUDController {

    private final OrderCRUDService orderCRUDService;

    @Autowired
    public OrderCRUDController(final OrderCRUDService orderCRUDService) {
        this.orderCRUDService = orderCRUDService;
    }

    @PostMapping("/orders")
    public ResponseEntity createOrders(@RequestBody final OrderCreateRequestDto requestDto) {
        checkRequest(requestDto.getOrders());
        return ResponseEntity.ok(orderCRUDService.createOrders(requestDto));
    }

    private void checkRequest(final List<OrderCreateData> requestOrders) {
        if (requestOrders.isEmpty()) {
            throw new OrderRequestEmptyException();
        }
    }
}
