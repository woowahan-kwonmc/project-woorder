package com.bamin.woorder.payment.domain.exception;

public class InvalidOrdersException extends RuntimeException {

    private static final String INVALID_PAYMENT_ORDERS_EXCEPTION = "주문은 비어있을 수 없습니다.";

    public InvalidOrdersException() {
        super(INVALID_PAYMENT_ORDERS_EXCEPTION);
    }
}
