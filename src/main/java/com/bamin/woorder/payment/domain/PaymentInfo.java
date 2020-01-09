package com.bamin.woorder.payment.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class PaymentInfo {

    @Embedded
    private PaymentMethod paymentMethod;

    @Embedded
    private PaymentPrice paymentPrice;

    @Embedded
    private PaymentStatus paymentStatus;

    public PaymentInfo(final PaymentMethod paymentMethod,
                       final PaymentPrice paymentPrice,
                       final PaymentStatus paymentStatus) {
        this.paymentMethod = paymentMethod;
        this.paymentPrice = paymentPrice;
        this.paymentStatus = paymentStatus;
    }
}
