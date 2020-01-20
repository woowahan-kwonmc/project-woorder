package com.bamin.woorder.order.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderConditionalRequestDto {
    private String status;
    private Long orderedBy;
    private int page;
    private int num;

    @Builder
    public OrderConditionalRequestDto(final String status,
                                      final Long orderedBy,
                                      final int page,
                                      final int num) {
        this.status = status;
        this.orderedBy = orderedBy;
        this.page = page;
        this.num = num;
    }
}
