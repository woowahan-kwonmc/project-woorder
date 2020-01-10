package com.bamin.woorder.coupon.domain;

import com.bamin.woorder.common.domain.ModifiableEntity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(of = "couponNo", callSuper = false)
@Table(name = "COUPON")
@Entity
public class Coupon extends ModifiableEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_no",
            updatable = false)
    private Long couponNo;

    @Embedded
    private CouponCode couponCode;

    @Embedded
    private CouponUseStatus couponUseStatus;

    @Embedded
    private CouponTypeInfo couponTypeInfo;

    @Embedded
    private CouponMember couponMember;

    @Embedded
    private CouponPayment couponPayment;

    public Coupon(final CouponCode couponCode,
                  final CouponUseStatus couponUseStatus,
                  final CouponTypeInfo couponTypeInfo,
                  final CouponMember couponMember,
                  final CouponPayment couponPayment) {
        this.couponCode = couponCode;
        this.couponUseStatus = couponUseStatus;
        this.couponTypeInfo = couponTypeInfo;
        this.couponMember = couponMember;
        this.couponPayment = couponPayment;
    }
}
