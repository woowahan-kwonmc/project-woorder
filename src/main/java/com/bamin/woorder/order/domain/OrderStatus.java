package com.bamin.woorder.order.domain;

import java.util.Arrays;

public enum OrderStatus {
    CREATE,
    IN_PROGRESS,
    COMPLETE,
    CANCELED;

    public static OrderStatus of(final String status) {
        return Arrays.stream(values())
                .filter(orderStatus -> orderStatus.name().equals(status))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
