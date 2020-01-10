package com.bamin.woorder.order.domain;

import com.bamin.woorder.common.domain.DeletableEntity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(of = "orderNo", callSuper = false)
@Table(name = "ORDER")
@Entity
public class Order extends DeletableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_no",
            updatable = false)
    private Long orderNo;

    @Embedded
    private OrderInfo orderInfo;

    @Embedded
    private OrderMember orderMember;

    @Embedded
    private OrderPayment orderPayment;

    public Order(final OrderInfo orderInfo,
                 final OrderMember orderMember,
                 final OrderPayment orderPayment) {
        this.orderInfo = orderInfo;
        this.orderMember = orderMember;
        this.orderPayment = orderPayment;
    }
}
