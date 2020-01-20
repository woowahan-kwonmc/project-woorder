package com.bamin.woorder.payment.domain.exception;

public class PaymentPriceRangeException extends RuntimeException {

    private static final String PAYMENT_PRICE_RANGE_EXCEPTION_MESSAGE = "지불 금액은 0 미만이 될 수 없습니다.";

    public PaymentPriceRangeException() {
        super(PAYMENT_PRICE_RANGE_EXCEPTION_MESSAGE);
    }
}
