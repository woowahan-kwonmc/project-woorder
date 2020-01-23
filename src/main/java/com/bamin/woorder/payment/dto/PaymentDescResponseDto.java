package com.bamin.woorder.payment.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class PaymentDescResponseDto {

    private Long no;
    private String method;
    private String status;
    private Long price;
    private LocalDateTime createTime;

    @Builder
    public PaymentDescResponseDto(final Long no,
                                  final String method,
                                  final String status,
                                  final Long price,
                                  final LocalDateTime createTime) {
        this.no = no;
        this.method = method;
        this.status = status;
        this.price = price;
        this.createTime = createTime;
    }
}
