package com.bamin.woorder.coupontype.domain;

import com.bamin.woorder.common.utils.TypeUtils;
import com.bamin.woorder.coupontype.domain.exception.CouponTypeDiscountAmountException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class CouponTypeDiscount {

    private static final String COUPON_DISCOUNT_TYPE_EXCEPTION_MESSAGE = "쿠폰 타입의 할인 금액은 숫자만 입력이 가능합니다.";

    @Column(name = "coupon_type_discount",
            nullable = false)
    private int amount;

    public CouponTypeDiscount(final int amount) {
        this.amount = amount;
    }

    public CouponTypeDiscount(final String couponTypeDiscount) {
        int amount = TypeUtils.parseToIntegerOrThrow(couponTypeDiscount, COUPON_DISCOUNT_TYPE_EXCEPTION_MESSAGE);
        checkDiscountAmount(amount);
        this.amount = amount;
    }

    private void checkDiscountAmount(final int amount) {
        if (amount <= 0) {
            throw new CouponTypeDiscountAmountException();
        }
    }
}
