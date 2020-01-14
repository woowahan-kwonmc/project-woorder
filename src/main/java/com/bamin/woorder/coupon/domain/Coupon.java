package com.bamin.woorder.coupon.domain;

import com.bamin.woorder.common.domain.ModifiableEntity;
import com.bamin.woorder.coupontype.domain.CouponType;
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

    public Coupon(CouponType couponType) {
        this.couponCode = new CouponCode();
        this.couponUseStatus = new CouponUseStatus(CouponStatus.USABLE);
        this.couponTypeInfo = new CouponTypeInfo(couponType);
    }

    public Coupon(final String code, final CouponType couponType) {
        this.couponCode = new CouponCode(code);
        this.couponUseStatus = new CouponUseStatus(CouponStatus.USABLE);
        this.couponTypeInfo = new CouponTypeInfo(couponType);
    }

    public String getCode() {
        return couponCode.getCouponCode();
    }

    public String getUseStatus() {
        return couponUseStatus.getCouponStatus().name();
    }
}
