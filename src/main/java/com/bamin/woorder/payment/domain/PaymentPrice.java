package com.bamin.woorder.payment.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class PaymentPrice {

    @Column(name = "\"payment_price\"",
            nullable = false)
    private int price;

    public PaymentPrice(final int price) {
        this.price = price;
    }
}
