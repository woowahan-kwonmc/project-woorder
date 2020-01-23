package com.bamin.woorder.coupon.domain;

import com.bamin.woorder.common.domain.ModifiableEntity;
import com.bamin.woorder.coupontype.domain.CouponType;
import com.bamin.woorder.member.domain.Member;
import com.bamin.woorder.payment.domain.Payment;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

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
        if (Objects.isNull(couponCode)) {
            return null;
        }
        return couponCode.getCouponCode();
    }

    public String getUseStatus() {
        return couponUseStatus.getCouponStatus().name();
    }

    public String getName() {
        return couponTypeInfo.getName();
    }

    public int getDiscount() {
        return couponTypeInfo.getDiscount();
    }

    public LocalDateTime getStartTime() {
        return couponTypeInfo.getStartTime();
    }

    public LocalDateTime getEndTime() {
        return couponTypeInfo.getEndTime();
    }

    public void useCoupon(final Member payMember) {
        checkOwner(payMember);
        this.couponUseStatus.useCoupon();
    }

    private void checkOwner(final Member payMember) {
        if (couponMember.isNotEquals(payMember)) {
            throw new InvalidCouponOwnerException();
        }
    }

    public String getMemberName() {
        if (Objects.isNull(couponMember)) {
            return null;
        }
        return couponMember.getName();
    }

    public void enrollMember(final Member requestMember) {
        if (!Objects.isNull(this.couponMember)) {
            throw new CouponAlreadyEnrolledException();
        }
        this.couponMember = new CouponMember(requestMember);
    }

    public void useCouponForPaymentByMember(final Payment payment, final Member member) {
        this.useCoupon(member);
        this.updateCouponPayment(payment);
    }

    public void updateCouponPayment(final Payment createdPayment) {
        this.couponPayment = new CouponPayment(createdPayment);
    }

    public void checkEnrollPeriod() {
        this.couponTypeInfo.checkEnrollPeriod();
    }
}
