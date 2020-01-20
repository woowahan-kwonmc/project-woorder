package com.bamin.woorder.payment.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PaymentCreateRequestDto {

    private String method;
    private Long memberNo;
    private List<Long> ordersNo;
    private List<Long> couponsNo;

    @Builder
    public PaymentCreateRequestDto(final String method,
                                   final Long memberNo,
                                   final List<Long> ordersNo,
                                   final List<Long> couponsNo) {
        this.method = method;
        this.memberNo = memberNo;
        this.ordersNo = ordersNo;
        this.couponsNo = couponsNo;
    }
}
