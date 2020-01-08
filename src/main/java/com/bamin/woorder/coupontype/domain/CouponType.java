package com.bamin.woorder.coupontype.domain;

import com.bamin.woorder.common.domain.ModifiableEntity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(of = "couponTypeNo", callSuper = false)
@Table(name = "COUPON_TYPE")
@Entity
public class CouponType extends ModifiableEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "coupon_type_no",
            updatable = false)
    private Long couponTypeNo;

    @Embedded
    private CouponTypeDiscount couponTypeDiscount;

    @Embedded
    private CouponTypeCount couponTypeCount;

    @Embedded
    private CouponTypeUsablePeriod couponTypeUsablePeriod;

    public CouponType(final CouponTypeDiscount couponTypeDiscount,
                      final CouponTypeCount couponTypeCount,
                      final CouponTypeUsablePeriod couponTypeUsablePeriod) {
        this.couponTypeDiscount = couponTypeDiscount;
        this.couponTypeCount = couponTypeCount;
        this.couponTypeUsablePeriod = couponTypeUsablePeriod;
    }
}
