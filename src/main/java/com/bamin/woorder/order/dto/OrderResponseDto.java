package com.bamin.woorder.order.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderResponseDto extends OrderResponseData {

    private Long orderedBy;
    private LocalDateTime modifiedTime;

    @Builder(builderMethodName = "dtoBuilder")
    public OrderResponseDto(final Long no,
                            final String status,
                            final long price,
                            final long quantity,
                            final LocalDateTime createTime,
                            final Long orderedBy,
                            final LocalDateTime modifiedTime) {
        super(no, status, price, quantity, createTime);
        this.orderedBy = orderedBy;
        this.modifiedTime = modifiedTime;
    }
}
