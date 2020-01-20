package com.bamin.woorder.payment.domain;

import com.bamin.woorder.coupon.domain.Coupon;
import com.bamin.woorder.order.domain.Order;
import com.bamin.woorder.payment.domain.exception.PayMethodNotFoundException;
import com.bamin.woorder.payment.domain.exception.PaymentPriceDiscountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.bamin.woorder.coupontype.CouponTypeConstants.TEST_COUPON_TYPE_DISCOUNT_1000;
import static com.bamin.woorder.coupontype.CouponTypeConstants.TEST_COUPON_TYPE_DISCOUNT_200000;
import static com.bamin.woorder.payment.domain.PaymentConstants.TEST_PAYMENT_MEMBER;
import static com.bamin.woorder.payment.domain.PaymentConstants.TEST_PAYMENT_MENU;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PaymentTest {

    @Test
    @DisplayName("결제 생성 성공")
    void successfullyConstructPayment() {
        assertDoesNotThrow(() -> Payment.builder()
                .method("CASH")
                .payMember(TEST_PAYMENT_MEMBER)
                .payCoupons(Collections.singletonList(new Coupon(TEST_COUPON_TYPE_DISCOUNT_1000)))
                .payOrders(Collections.singletonList(new Order(1, TEST_PAYMENT_MENU, TEST_PAYMENT_MEMBER)))
                .build());
    }

    @Test
    @DisplayName("결제 생성 실패, 결제 방법 오류")
    void failedConstructPaymentMethodNotFound() {
        assertThrows(PayMethodNotFoundException.class, () -> Payment.builder()
                .method("T-MONEY")
                .payMember(TEST_PAYMENT_MEMBER)
                .payCoupons(Collections.singletonList(new Coupon(TEST_COUPON_TYPE_DISCOUNT_1000)))
                .payOrders(Collections.singletonList(new Order(1, TEST_PAYMENT_MENU, TEST_PAYMENT_MEMBER)))
                .build());
    }

    @Test
    @DisplayName("결제 생성 실패, 할인 가격 오류")
    void failedConstructPaymentPriceRangeException() {
        assertThrows(PaymentPriceDiscountException.class, () -> Payment.builder()
                .method("BAEMIN_PAY")
                .payMember(TEST_PAYMENT_MEMBER)
                .payCoupons(Collections.singletonList(new Coupon(TEST_COUPON_TYPE_DISCOUNT_200000)))
                .payOrders(Collections.singletonList(new Order(1, TEST_PAYMENT_MENU, TEST_PAYMENT_MEMBER)))
                .build());

    }
}