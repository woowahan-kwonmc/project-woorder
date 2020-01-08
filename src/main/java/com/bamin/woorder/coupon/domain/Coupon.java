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

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "coupon_no",
            updatable = false)
    private Long couponNo;

    @Embedded
    private CouponCode couponCode;

    @Embedded
    private CouponUseStatus couponUseStatus;

    @Embedded
    private CouponMember couponMember;

    public Coupon(final CouponCode couponCode,
                  final CouponUseStatus couponUseStatus,
                  final CouponMember couponMember) {
        this.couponCode = couponCode;
        this.couponUseStatus = couponUseStatus;
        this.couponMember = couponMember;
    }
}
