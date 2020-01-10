package com.bamin.woorder.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class OrderPrice {

    @Column(name = "order_price",
            nullable = false)
    private int orderPrice;

    public OrderPrice(final int orderPrice) {
        this.orderPrice = orderPrice;
    }
}
