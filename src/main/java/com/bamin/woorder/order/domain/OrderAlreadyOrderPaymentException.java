package com.bamin.woorder.order.domain;

public class OrderAlreadyOrderPaymentException extends RuntimeException {

    private static final String ORDER_CANNOT_CANCEL_EXCEPTION_MESSAGE = "이미 결재 단계가 진행 중인 주문은 취소할 수 없습니다.";

    OrderAlreadyOrderPaymentException() {
        super(ORDER_CANNOT_CANCEL_EXCEPTION_MESSAGE);
    }
}
