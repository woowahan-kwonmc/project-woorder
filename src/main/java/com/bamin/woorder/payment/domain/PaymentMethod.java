package com.bamin.woorder.payment.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class PaymentMethod {

    @Column(name = "\"payment_method\"",
            nullable = false,
            columnDefinition = "enum('CASH', 'CARD', 'KAKAO_PAY', 'BAMIN_PAY', 'TOSS_PAY') default 'CASH'")
    private PayMethod payMethod;

    public PaymentMethod(final PayMethod payMethod) {
        this.payMethod = payMethod;
    }
}
