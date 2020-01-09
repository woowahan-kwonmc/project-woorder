package com.bamin.woorder.payment.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class PaymentStatus {

    @Column(name = "\"payment_status\"",
            nullable = false,
            columnDefinition = "enum('INCOMPLETE', 'COMPLETE', 'CANCELED') default 'INCOMPLETE'")
    private PayStatus payStatus;

    public PaymentStatus(final PayStatus payStatus) {
        this.payStatus = payStatus;
    }
}
