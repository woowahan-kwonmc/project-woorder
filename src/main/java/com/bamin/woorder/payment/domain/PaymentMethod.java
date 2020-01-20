package com.bamin.woorder.payment.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
class PaymentMethod {

    @Column(name = "payment_method",
            nullable = false)
    @Enumerated(EnumType.STRING)
    private PayMethod payMethod;

    PaymentMethod(final String method) {
        this.payMethod = PayMethod.of(method);
    }
}
