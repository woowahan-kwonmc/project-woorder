package com.bamin.woorder.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class OrderInfo {

    @Embedded
    private OrderMenu orderMenu;

    @Embedded
    private OrderQuantity orderQuantity;

    @Embedded
    private OrderPrice orderPrice;

    @Embedded
    private OrderProcessStatus orderProcessStatus;

    public OrderInfo(final OrderMenu orderMenu,
                     final OrderQuantity orderQuantity,
                     final OrderPrice orderPrice,
                     final OrderProcessStatus orderProcessStatus) {
        this.orderMenu = orderMenu;
        this.orderQuantity = orderQuantity;
        this.orderPrice = orderPrice;
        this.orderProcessStatus = orderProcessStatus;
    }
}
