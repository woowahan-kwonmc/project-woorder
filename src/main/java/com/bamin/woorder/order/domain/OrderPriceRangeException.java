package com.bamin.woorder.order.domain;

import com.bamin.woorder.order.OrderException;

public class OrderPriceRangeException extends OrderException {

    private static final String ORDER_PRICE_RANGE_EXCEPTION_MESSAGE = "최소 주문 금액은 음수가 될 수 없습니다.";

    OrderPriceRangeException() {
        super(ORDER_PRICE_RANGE_EXCEPTION_MESSAGE);
    }
}
