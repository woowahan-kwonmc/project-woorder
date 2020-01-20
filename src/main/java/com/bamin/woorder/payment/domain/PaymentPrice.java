package com.bamin.woorder.payment.domain;

import com.bamin.woorder.payment.domain.exception.PaymentPriceRangeException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
class PaymentPrice {

    @Column(name = "payment_price",
            nullable = false)
    private long price;

    PaymentPrice(final long price) {
        checkPriceRange(price);
        this.price = price;
    }

    private void checkPriceRange(final long price) {
        if (price < 0) {
            throw new PaymentPriceRangeException();
        }
    }
}
