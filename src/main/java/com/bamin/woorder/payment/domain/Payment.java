package com.bamin.woorder.payment.domain;

import com.bamin.woorder.common.domain.DeletableEntity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(of = "paymentNo", callSuper = false)
@Table(name = "\"PAYMENT\"", schema = "woorder")
@Entity
public class Payment extends DeletableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"payment_no\"", updatable = false)
    private Long paymentNo;

    @Embedded
    private PaymentInfo paymentInfo;

    @Embedded
    private PaymentMember paymentMember;

    @Embedded
    private PaymentCoupons paymentCoupons;

    @Embedded
    private PaymentOrders paymentOrders;

    public Payment(final PaymentInfo paymentInfo,
                   final PaymentMember paymentMember,
                   final PaymentCoupons paymentCoupons,
                   final PaymentOrders paymentOrders) {
        this.paymentInfo = paymentInfo;
        this.paymentMember = paymentMember;
        this.paymentCoupons = paymentCoupons;
        this.paymentOrders = paymentOrders;
    }
}
