package com.bamin.woorder.order.presentation;

import com.bamin.woorder.order.OrderException;

public class OrderRequestEmptyException extends OrderException {

    private static final String ORDER_REQUEST_EMPTY_EXCEPTION_MESSAGE = "주문은 비어있을 수 없습니다.";

    OrderRequestEmptyException() {
        super(ORDER_REQUEST_EMPTY_EXCEPTION_MESSAGE);
    }
}
