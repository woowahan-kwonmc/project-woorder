package com.bamin.woorder.order.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
class OrderInfo {

    @Embedded
    private OrderMenu orderMenu;

    @Embedded
    private OrderQuantity orderQuantity;

    @Embedded
    private OrderPrice orderPrice;

    @Embedded
    private OrderProcessStatus orderProcessStatus;

    @Builder
    OrderInfo(final OrderMenu orderMenu, final int orderQuantity, final int orderMenuPrice) {
        this.orderMenu = orderMenu;
        this.orderQuantity = new OrderQuantity(orderQuantity);
        this.orderPrice = OrderPrice.of(orderMenuPrice, orderQuantity);
        this.orderProcessStatus = OrderProcessStatus.initialize();
    }

    String getStatus() {
        return orderProcessStatus.getStatus();
    }

    long getPrice() {
        return orderPrice.getOrderPrice();
    }

    long getQuantity() {
        return orderQuantity.getQuantity();
    }
}
