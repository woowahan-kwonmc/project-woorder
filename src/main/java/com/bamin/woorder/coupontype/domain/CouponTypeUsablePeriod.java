package com.bamin.woorder.coupontype.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class CouponTypeUsablePeriod {

    @Column(name = "coupon_usable_start_time",
            nullable = false)
    private LocalDateTime startTime;

    @Column(name = "coupon_usable_end_time",
            nullable = false)
    private LocalDateTime endTime;

    public CouponTypeUsablePeriod(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
