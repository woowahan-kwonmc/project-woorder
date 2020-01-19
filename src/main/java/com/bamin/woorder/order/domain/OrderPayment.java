package com.bamin.woorder.order.domain;

import com.bamin.woorder.payment.domain.Payment;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class OrderPayment {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_payment_no")
    private Payment orderPayment;

    public OrderPayment(final Payment orderPayment) {
        this.orderPayment = orderPayment;
    }
}
