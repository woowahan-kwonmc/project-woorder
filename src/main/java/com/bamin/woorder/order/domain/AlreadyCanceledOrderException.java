package com.bamin.woorder.order.domain;

public class AlreadyCanceledOrderException extends RuntimeException {

    private static final String ALREADY_CANCELED_ORDER_EXCEPTION_MESSAGE = "이미 취소된 주문입니다.";

    AlreadyCanceledOrderException() {
        super(ALREADY_CANCELED_ORDER_EXCEPTION_MESSAGE);
    }
}
