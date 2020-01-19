package com.bamin.woorder.order.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderCreateRequestDto {

    private List<OrderCreateData> orders;

    public OrderCreateRequestDto(final List<OrderCreateData> orders) {
        this.orders = orders;
    }
}
