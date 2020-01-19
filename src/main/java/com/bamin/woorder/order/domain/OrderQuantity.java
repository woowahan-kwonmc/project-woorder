package com.bamin.woorder.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
class OrderQuantity {

    @Column(name = "order_quantity",
            nullable = false,
            updatable = false)
    private long quantity;

    OrderQuantity(final long quantity) {
        checkQuantity(quantity);
        this.quantity = quantity;
    }

    private void checkQuantity(final long quantity) {
        if (quantity < 1) {
            throw new OrderQuantityRangeException();
        }
    }
}
