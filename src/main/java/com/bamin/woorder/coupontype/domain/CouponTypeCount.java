package com.bamin.woorder.coupontype.domain;

import com.bamin.woorder.common.utils.TypeUtils;
import com.bamin.woorder.coupontype.domain.exception.CouponTypeCountRangeException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class CouponTypeCount {

    private static final String COUPON_COUNT_TYPE_CHECK_EXCEPTION_MESSAGE = "쿠폰 타입의 수량은 숫자만 입력이 가능합니다.";
    private static final int COUPON_TYPE_COUNT_LOWER_BOUND = 1;

    @Column(name = "coupon_type_count",
            nullable = false)
    private Long count;

    public CouponTypeCount(final Long count) {
        this.count = count;
    }

    public CouponTypeCount(final String couponTypeCount) {
        long count = TypeUtils.parseToLongOrThrow(couponTypeCount, COUPON_COUNT_TYPE_CHECK_EXCEPTION_MESSAGE);
        checkCouponTypeCountRange(count);
        this.count = count;
    }

    private void checkCouponTypeCountRange(final long count) {
        if (count < COUPON_TYPE_COUNT_LOWER_BOUND) {
            throw new CouponTypeCountRangeException();
        }
    }
}
