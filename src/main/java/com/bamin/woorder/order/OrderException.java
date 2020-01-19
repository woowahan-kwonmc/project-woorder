package com.bamin.woorder.order;

import lombok.Getter;

@Getter
public class OrderException extends RuntimeException {

    public OrderException(final String message) {
        super(message);
    }
}
