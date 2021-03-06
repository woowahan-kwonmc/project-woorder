package com.bamin.woorder.order.domain;

import com.bamin.woorder.common.domain.DeletableEntity;
import com.bamin.woorder.member.domain.Member;
import com.bamin.woorder.menu.domain.Menu;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(of = "orderNo", callSuper = false)
@Table(name = "\"ORDER\"")
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

    @Builder
    public Order(final int quantity, final Menu savedMenu, final Member savedMember) {
        this.orderInfo = OrderInfo.builder()
                .orderQuantity(quantity)
                .orderMenu(new OrderMenu(savedMenu))
                .orderMenuPrice(savedMenu.getPrice())
                .build();
        this.orderMember = new OrderMember(savedMember);
    }

    public String getStatus() {
        return orderInfo.getStatus();
    }

    public long getPrice() {
        return orderInfo.getPrice();
    }

    public long getQuantity() {
        return orderInfo.getQuantity();
    }

    public Long getOrderMemberNo() {
        return orderMember.getMemberNo();
    }

    public Order cancelOrder() {
        checkAlreadyPayment();
        checkAlreadyProcessed();
        orderInfo.cancelOrder();
        super.softDelete();
        return this;
    }

    public void updatePaymentInfo(final Member payMember) {
        this.updateInProgress();
        this.updatePaymentMember(payMember);
    }

    public void updateInProgress() {
        checkAlreadyCanceled();
        this.orderInfo.updateInProgress();
    }

    public void updatePaymentMember(final Member payMember) {
        this.orderMember = new OrderMember(payMember);
    }

    private void checkAlreadyCanceled() {
        orderInfo.getOrderProcessStatus().checkAlreadyCanceled();
    }

    private void checkAlreadyProcessed() {
        orderInfo.getOrderProcessStatus().checkAlreadyProcessed();
    }

    private void checkAlreadyPayment() {
        if (this.orderPayment != null) {
            throw new OrderAlreadyOrderPaymentException();
        }
    }
}
