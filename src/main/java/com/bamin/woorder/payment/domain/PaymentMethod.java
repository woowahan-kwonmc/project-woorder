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
public class PaymentMethod {

    @Column(name = "payment_method",
            nullable = false)
    @Enumerated(EnumType.STRING)
    private PayMethod payMethod;

    public PaymentMethod(final PayMethod payMethod) {
        this.payMethod = payMethod;
    }
}
