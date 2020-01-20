package com.bamin.woorder.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class OrderProcessStatus {

    @Column(name = "order_status",
            nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private OrderProcessStatus(final OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    static OrderProcessStatus initialize() {
        return new OrderProcessStatus(OrderStatus.CREATE);
    }

    public String getStatus() {
        return orderStatus.name();
    }

    void cancel() {
        checkAlreadyCanceled();
        this.orderStatus = OrderStatus.CANCELED;
    }

    void inProgress() {
        checkAlreadyProcessed();
        this.orderStatus = OrderStatus.IN_PROGRESS;
    }

    private void checkAlreadyCanceled() {
        if (this.orderStatus.equals(OrderStatus.CANCELED)) {
            throw new AlreadyCanceledOrderException();
        }
    }

    private void checkAlreadyProcessed() {
        if (!orderStatus.equals(OrderStatus.CREATE)) {
            throw new AlreadyProcessedOrderException();
        }
    }
}
