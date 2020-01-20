package com.bamin.woorder.order.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderResponseData {

    private Long no;
    private String status;
    private long price;
    private long quantity;
    private LocalDateTime createTime;

    @Builder
    OrderResponseData(final Long no,
                      final String status,
                      final long price,
                      final long quantity,
                      final LocalDateTime createTime) {
        this.no = no;
        this.status = status;
        this.price = price;
        this.quantity = quantity;
        this.createTime = createTime;
    }
}
