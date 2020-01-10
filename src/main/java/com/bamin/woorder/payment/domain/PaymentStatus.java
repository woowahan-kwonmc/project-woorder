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
public class PaymentStatus {

    @Column(name = "payment_status",
            nullable = false)
    @Enumerated(EnumType.STRING)
    private PayStatus payStatus;

    public PaymentStatus(final PayStatus payStatus) {
        this.payStatus = payStatus;
    }
}
