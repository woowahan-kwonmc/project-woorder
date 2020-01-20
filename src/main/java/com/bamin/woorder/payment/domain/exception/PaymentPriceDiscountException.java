package com.bamin.woorder.payment.domain.exception;

public class PaymentPriceDiscountException extends RuntimeException {

    private static final String INVALID_DISCOUNT_EXCEPTION_MESSAGE = "할인 금액이 주문 금액보다 높을 수 없습니다.";

    public PaymentPriceDiscountException() {
        super(INVALID_DISCOUNT_EXCEPTION_MESSAGE);
    }
}
