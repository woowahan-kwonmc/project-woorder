package com.bamin.woorder.coupontype.domain;

import com.bamin.woorder.common.domain.ModifiableEntity;
import com.bamin.woorder.coupon.domain.Coupon;
import com.bamin.woorder.coupontype.domain.exception.CouponTypeCountExcessException;
import com.bamin.woorder.coupontype.domain.exception.IllegalCouponTypeException;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@EqualsAndHashCode(of = "couponTypeNo", callSuper = false)
@Table(name = "COUPON_TYPE")
@Entity
public class CouponType extends ModifiableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private CouponTypeHasCode couponTypeHasCode;

    @Embedded
    private CouponTypeUsablePeriod couponTypeUsablePeriod;

    @Embedded
    private CouponTypeCoupons couponTypeCoupons;

    @Builder
    public CouponType(final String couponTypeName,
                      final String couponTypeCount,
                      final String couponTypeDiscount,
                      final boolean couponTypeHasCode,
                      final String startTime,
                      final String endTime) {
        this.couponTypeName = new CouponTypeName(couponTypeName);
        this.couponTypeCount = new CouponTypeCount(couponTypeCount);
        this.couponTypeHasCode = new CouponTypeHasCode(couponTypeHasCode);
        this.couponTypeDiscount = new CouponTypeDiscount(couponTypeDiscount);
        this.couponTypeUsablePeriod = new CouponTypeUsablePeriod(startTime, endTime);
    }

    public boolean checkCreatablePeriod() {
        return couponTypeUsablePeriod.checkCreatablePeriod();
    }

    public Long getTotalCountAfterGenerate(final Long currentCouponCount, final Long requestCounts) {
        long totalCountAfterGenerate = currentCouponCount + requestCounts;
        if (totalCountAfterGenerate < this.getCount()) {
            return totalCountAfterGenerate;
        }

        throw new CouponTypeCountExcessException(currentCouponCount, requestCounts, this.getCount());
    }

    public void checkDownloadable() {
        if (this.getHasCode()) {
            throw new IllegalCouponTypeException(this.getCouponTypeNo(), this.getName(), this.getHasCode());
        }
    }
    public void checkHasCode() {
        if (!this.getHasCode()) {
            throw new IllegalCouponTypeException(this.getCouponTypeNo(), this.getName(), this.getHasCode());
        }
    }

    public Long getNo() {
        return getCouponTypeNo();
    }

    public String getName() {
        return couponTypeName.getCouponTypeName();
    }

    public boolean getHasCode() {
        return couponTypeHasCode.isCouponTypeHasCode();
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
