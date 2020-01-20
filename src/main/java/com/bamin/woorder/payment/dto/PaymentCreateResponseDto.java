package com.bamin.woorder.payment.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentCreateResponseDto {

    private Long no;
    private String method;
    private String status;
    private Long price;
    private LocalDateTime createTime;
    private LocalDateTime modifiedTime;

    @Builder
    public PaymentCreateResponseDto(final Long no,
                                    final String method,
                                    final String status,
                                    final Long price,
                                    final LocalDateTime createTime,
                                    final LocalDateTime modifiedTime) {
        this.no = no;
        this.method = method;
        this.status = status;
        this.price = price;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
    }
}
