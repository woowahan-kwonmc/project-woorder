package com.bamin.woorder.payment.domain;

import com.bamin.woorder.order.domain.Order;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
class PaymentOrders {

    @OneToMany(mappedBy = "orderPayment", fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    static PaymentOrders init(final List<Order> payOrders) {
        return new PaymentOrders().addAll(payOrders);
    }

    private PaymentOrders addAll(final List<Order> payOrders) {
        orders.addAll(payOrders);
        return this;
    }
}
