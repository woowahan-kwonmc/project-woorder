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
            nullable = false,
            updatable = false)
    private Long orderPrice;

    private OrderPrice(final long orderPrice) {
        checkPriceRange(orderPrice);
        this.orderPrice = orderPrice;
    }

    public static OrderPrice of(final int menuPrice, final int quantity) {
        return new OrderPrice(menuPrice * quantity);
    }

    private void checkPriceRange(final long orderPrice) {
        if (orderPrice < 0) {
            throw new OrderPriceRangeException();
        }
    }
}
