package com.bamin.woorder.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class OrderQuantity {

    @Column(name = "order_quantity",
            nullable = false)
    private int quantity;

    public OrderQuantity(final int quantity) {
        this.quantity = quantity;
    }
}
