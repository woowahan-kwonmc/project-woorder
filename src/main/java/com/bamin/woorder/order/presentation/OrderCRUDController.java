package com.bamin.woorder.order.presentation;

import com.bamin.woorder.order.application.OrderCRUDService;
import com.bamin.woorder.order.dto.OrderConditionalRequestDto;
import com.bamin.woorder.order.dto.OrderCreateData;
import com.bamin.woorder.order.dto.OrderCreateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1")
@RestController
@CrossOrigin({"http://localhost:3000"})
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

    @GetMapping("/orders/{orderNo}")
    public ResponseEntity findOrder(@PathVariable final Long orderNo) {
        return ResponseEntity.ok(orderCRUDService.findOrderByOrderNo(orderNo));
    }

    @GetMapping("/orders")
    public ResponseEntity findConditionalOrders(@OrderConditionalRequestParams final OrderConditionalRequestDto requestDto) {
        return ResponseEntity.ok(orderCRUDService.findConditionalOrders(requestDto));
    }

    @PatchMapping("/orders")
    public ResponseEntity cancelOrder(@RequestParam final Long orderNo) {
        return ResponseEntity.ok(orderCRUDService.cancelOrder(orderNo));
    }

    private void checkRequest(final List<OrderCreateData> requestOrders) {
        if (requestOrders.isEmpty()) {
            throw new OrderRequestEmptyException();
        }
    }
}
