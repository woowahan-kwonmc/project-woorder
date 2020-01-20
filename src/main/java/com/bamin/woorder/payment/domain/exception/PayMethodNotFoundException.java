package com.bamin.woorder.payment.domain.exception;

import lombok.Getter;

@Getter
public class PayMethodNotFoundException extends RuntimeException {

    public static final String PAY_METHOD_NOT_FOUND_EXCEPTION_MESSAGE = "존재하지 않는 결제 수단입니다.";

    public PayMethodNotFoundException() {
        super(PAY_METHOD_NOT_FOUND_EXCEPTION_MESSAGE);
    }
}
