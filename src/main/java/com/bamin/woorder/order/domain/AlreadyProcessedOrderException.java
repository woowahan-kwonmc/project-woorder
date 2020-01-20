package com.bamin.woorder.order.domain;

public class AlreadyProcessedOrderException extends RuntimeException {

    private static final String ALREADY_PROCESSED_ORDER_EXCEPTION_MESSAGE = "이미 처리된 주문입니다.";

    AlreadyProcessedOrderException() {
        super(ALREADY_PROCESSED_ORDER_EXCEPTION_MESSAGE);
    }
}
