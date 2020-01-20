package com.bamin.woorder.order.application;


import com.bamin.woorder.order.OrderException;
import lombok.Getter;

@Getter
public class NotFoundOrderException extends OrderException {

    private static final String NOT_FOUND_ORDER_EXCEPTION_MESSAGE = "해당하는 주문이 존재하지 않습니다.";

    private final Long requestNo;

    NotFoundOrderException(final Long requestNo) {
        super(NOT_FOUND_ORDER_EXCEPTION_MESSAGE);
        this.requestNo = requestNo;
    }
}
