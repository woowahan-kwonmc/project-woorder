package com.bamin.woorder.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class OrderProcessStatus {

    @Column(name = "\"order_status\"",
            nullable = false,
            columnDefinition = "enum('CREATE', 'ONPROGRESS', 'COMPLETE', 'CANCELED') default 'CREATE'")
    private OrderStatus orderStatus;

    public OrderProcessStatus(final OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
