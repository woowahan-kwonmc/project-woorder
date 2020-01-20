package com.bamin.woorder.order.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderCreateData {

    private Long menuNo;
    private Long memberNo;
    private int quantity;

    public OrderCreateData(final Long menuNo, final Long memberNo, final int quantity) {
        this.menuNo = menuNo;
        this.memberNo = memberNo;
        this.quantity = quantity;
    }
}
