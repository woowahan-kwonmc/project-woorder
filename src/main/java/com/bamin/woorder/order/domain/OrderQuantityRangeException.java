package com.bamin.woorder.order.domain;

import com.bamin.woorder.order.OrderException;

public class OrderQuantityRangeException extends OrderException {

    private static final String ORDER_QUANTITY_EXCEPTION_MESSAGE = "기본 주문 수량은 1 이상 입니다.";

    OrderQuantityRangeException() {
        super(ORDER_QUANTITY_EXCEPTION_MESSAGE);
    }
}
