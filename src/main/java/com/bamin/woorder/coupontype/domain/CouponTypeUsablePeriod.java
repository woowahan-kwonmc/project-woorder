package com.bamin.woorder.coupontype.domain;


import com.bamin.woorder.common.infra.LocalDateTimePersistenceConverter;
import com.bamin.woorder.common.utils.TypeUtils;
import com.bamin.woorder.coupontype.domain.exception.CouponTypePeriodException;
import com.bamin.woorder.coupontype.domain.exception.CouponTypeUsablePeriodException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class CouponTypeUsablePeriod {

    @Column(name = "coupon_usable_start_time",
            insertable = false,
            updatable = false,
            nullable = true)
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    private LocalDateTime startTime;

    @Column(name = "coupon_usable_end_time",
            insertable = false,
            updatable = false,
            nullable = true)
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    private LocalDateTime endTime;

    public CouponTypeUsablePeriod(final String startTime, final String endTime) {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime unCheckedStartTime = TypeUtils.parseToTimeOrThrow(startTime);
        LocalDateTime unCheckedEndTime = TypeUtils.parseToTimeOrThrow(endTime);
        checkValidUsablePeriod(currentTime, unCheckedStartTime, unCheckedEndTime);
        this.startTime = unCheckedStartTime;
        this.endTime = unCheckedEndTime;
    }

    private void checkValidUsablePeriod(final LocalDateTime currentTime,
                                        final LocalDateTime unCheckedStartTime,
                                        final LocalDateTime unCheckedEndTime) {
        if (isValidPeriod(currentTime, unCheckedStartTime, unCheckedEndTime)) {
            throw new CouponTypeUsablePeriodException(currentTime, unCheckedStartTime, unCheckedEndTime);
        }
    }

    private boolean isValidPeriod(final LocalDateTime currentTime,
                                  final LocalDateTime unCheckedStartTime,
                                  final LocalDateTime unCheckedEndTime) {
        return unCheckedStartTime.isBefore(currentTime)
                || unCheckedEndTime.isBefore(currentTime)
                || unCheckedEndTime.isBefore(unCheckedStartTime)
                || unCheckedStartTime.isEqual(unCheckedEndTime);
    }

    boolean checkCreatablePeriod() {
        LocalDateTime currentTime = LocalDateTime.now();
        if (currentTime.isAfter(endTime)) {
            throw new CouponTypePeriodException(endTime);
        }
        return true;
    }
}
