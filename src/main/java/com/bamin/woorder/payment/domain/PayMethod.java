package com.bamin.woorder.payment.domain;

import com.bamin.woorder.payment.domain.exception.PayMethodNotFoundException;

import java.util.Arrays;

public enum PayMethod {
    CASH,
    CARD,
    KAKAO_PAY,
    BAEMIN_PAY,
    TOSS_PAY;

    public static PayMethod of(final String method) {
        return Arrays.stream(values())
                .filter(value -> value.name().equals(method))
                .findFirst()
                .orElseThrow(PayMethodNotFoundException::new);
    }
}
