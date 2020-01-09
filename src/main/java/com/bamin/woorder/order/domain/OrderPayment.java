package com.bamin.woorder.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class OrderPayment {

    @Column(name = "order_payment_no")
    private Long orderPaymentNo;

    public OrderPayment(final Long orderPaymentNo) {
        this.orderPaymentNo = orderPaymentNo;
    }
}
