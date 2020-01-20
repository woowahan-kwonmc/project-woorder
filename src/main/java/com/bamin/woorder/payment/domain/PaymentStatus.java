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
class PaymentStatus {

    @Column(name = "payment_status",
            nullable = false)
    @Enumerated(EnumType.STRING)
    private PayStatus payStatus;

    private PaymentStatus(final PayStatus payStatus) {
        this.payStatus = payStatus;
    }

    static PaymentStatus initialize(final PaymentMethod paymentMethod) {
        if (PayMethod.CASH.equals(paymentMethod.getPayMethod())) {
            return new PaymentStatus(PayStatus.INCOMPLETE);
        }
        return new PaymentStatus(PayStatus.COMPLETE);
    }
}
