package com.bamin.woorder.coupontype.domain;

import com.bamin.woorder.common.domain.ModifiableEntity;
import com.bamin.woorder.coupon.domain.Coupon;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(of = "couponTypeNo", callSuper = false)
@Table(name = "COUPON_TYPE")
@Entity
public class CouponType extends ModifiableEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_type_no",
            updatable = false)
    private Long couponTypeNo;

    @Embedded
    private CouponTypeName couponTypeName;

    @Embedded
    private CouponTypeDiscount couponTypeDiscount;

    @Embedded
    private CouponTypeCount couponTypeCount;

    @Embedded
    private CouponTypeCoupons couponTypeCoupons;

    @Embedded
    private CouponTypeUsablePeriod couponTypeUsablePeriod;

    @Builder
    public CouponType(final String couponTypeName,
                      final String couponTypeCount,
                      final String couponTypeDiscount,
                      final String startTime,
                      final String endTime) {
        this.couponTypeName = new CouponTypeName(couponTypeName);
        this.couponTypeCount = new CouponTypeCount(couponTypeCount);
        this.couponTypeDiscount = new CouponTypeDiscount(couponTypeDiscount);
        this.couponTypeUsablePeriod = new CouponTypeUsablePeriod(startTime, endTime);
    }

    public Long getNo() {
        return getCouponTypeNo();
    }

    public String getName() {
        return couponTypeName.getCouponTypeName();
    }

    public int getDiscount() {
        return couponTypeDiscount.getAmount();
    }

    public Long getCount() {
        return couponTypeCount.getCount();
    }

    public List<Coupon> getCoupons() {
        return couponTypeCoupons.getCoupons();
    }

    public LocalDateTime getStartTime() {
        return couponTypeUsablePeriod.getStartTime();
    }

    public LocalDateTime getEndTime() {
        return couponTypeUsablePeriod.getEndTime();
    }
}
