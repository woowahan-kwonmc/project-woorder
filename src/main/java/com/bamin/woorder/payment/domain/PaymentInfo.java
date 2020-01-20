package com.bamin.woorder.payment.domain;

import com.bamin.woorder.coupon.domain.Coupon;
import com.bamin.woorder.order.domain.Order;
import com.bamin.woorder.payment.domain.exception.PaymentPriceDiscountException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
class PaymentInfo {

    @Embedded
    private PaymentMethod paymentMethod;

    @Embedded
    private PaymentPrice paymentPrice;

    @Embedded
    private PaymentStatus paymentStatus;

    @Embedded
    private PaymentCoupons paymentCoupons;

    @Embedded
    private PaymentOrders paymentOrders;

    @Builder
    PaymentInfo(final String method,
                final List<Order> payOrders,
                final List<Coupon> payCoupons) {
        this.paymentMethod = new PaymentMethod(method);
        this.paymentPrice = calculatePrice(payOrders, payCoupons);
        this.paymentStatus = PaymentStatus.initialize(this.paymentMethod);
        this.paymentCoupons = PaymentCoupons.init(payCoupons);
        this.paymentOrders = PaymentOrders.init(payOrders);
    }

    private PaymentPrice calculatePrice(final List<Order> payOrders, final List<Coupon> payCoupons) {
        long totalPrice = payOrders.stream()
                .map(Order::getPrice)
                .reduce(0L, Long::sum);
        long totalDiscount = payCoupons.stream()
                .map(Coupon::getDiscount)
                .reduce(0, Integer::sum);
        checkPriceAndDiscount(totalPrice, totalDiscount);
        return new PaymentPrice(totalPrice - totalDiscount);
    }

    private void checkPriceAndDiscount(final long totalPrice, final long totalDiscount) {
        if (totalPrice - totalDiscount <= 0) {
            throw new PaymentPriceDiscountException();
        }
    }

    public Long getPrice() {
        return paymentPrice.getPrice();
    }

    public String getMethod() {
        return paymentMethod.getPayMethod().name();
    }

    public String getStatus() {
        return paymentStatus.getPayStatus().name();
    }
}
