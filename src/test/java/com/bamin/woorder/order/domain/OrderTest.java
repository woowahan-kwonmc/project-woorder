package com.bamin.woorder.order.domain;

import com.bamin.woorder.member.domain.Member;
import com.bamin.woorder.menu.domain.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderTest {

    @Test
    @DisplayName("Order 생성 성공")
    void succeedConstructOrder() {
        assertDoesNotThrow(() -> Order.builder()
                .quantity(1)
                .savedMember(new Member("권민철"))
                .savedMenu(new Menu("양념치킨", "18000"))
                .build());
    }

    @Test
    @DisplayName("Order 생성 실패, 수량 범위 오류")
    void failedConstructQuantityRange() {
        assertThrows(OrderQuantityRangeException.class, () -> Order.builder()
                .quantity(0)
                .savedMember(new Member("권민철"))
                .savedMenu(new Menu("양념치킨", "18000"))
                .build());
    }

    @Test
    @DisplayName("Order 취소 성공")
    void succeedCancelOrder() {
        Order order = Order.builder()
                .quantity(1)
                .savedMember(new Member("권민철"))
                .savedMenu(new Menu("양념치킨", "18000"))
                .build();
        assertDoesNotThrow(order::cancelOrder);
    }

    @Test
    @DisplayName("Order 취소 실패, 이미 취소됨")
    void failedCancelOrder() {
        Order order = Order.builder()
                .quantity(1)
                .savedMember(new Member("권민철"))
                .savedMenu(new Menu("양념치킨", "18000"))
                .build();
        order.cancelOrder();
        assertThrows(AlreadyCanceledOrderException.class, order::cancelOrder);
    }
}