package com.bamin.woorder.order.domain;

import com.bamin.woorder.payment.domain.Payment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class OrderPayment {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_payment_no")
    private Payment orderPayment;

    public OrderPayment(final Payment orderPayment) {
        this.orderPayment = orderPayment;
    }
}
