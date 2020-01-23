package com.bamin.woorder.payment.domain;

import com.bamin.woorder.common.domain.DeletableEntity;
import com.bamin.woorder.coupon.domain.Coupon;
import com.bamin.woorder.member.domain.Member;
import com.bamin.woorder.order.domain.Order;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(of = "paymentNo", callSuper = false)
@Table(name = "PAYMENT")
@Entity
public class Payment extends DeletableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_no", updatable = false)
    private Long paymentNo;

    @Embedded
    private PaymentInfo paymentInfo;

    @Embedded
    private PaymentMember paymentMember;

    @Builder
    public Payment(final String method,
                   final Member payMember,
                   final List<Order> payOrders,
                   final List<Coupon> payCoupons) {
        this.paymentInfo = new PaymentInfo(method, payOrders, payCoupons);
        this.paymentMember = new PaymentMember(payMember);
    }

    public Long getPrice() {
        return paymentInfo.getPrice();
    }

    public String getMethod() {
        return paymentInfo.getMethod();
    }

    public String getStatus() {
        return paymentInfo.getStatus();
    }
}
