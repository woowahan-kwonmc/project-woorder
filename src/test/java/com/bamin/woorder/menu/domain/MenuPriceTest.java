package com.bamin.woorder.menu.domain;

import com.bamin.woorder.common.exception.InvalidParameterTypeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MenuPriceTest {

    @Test
    @DisplayName("메뉴 가격 객체 생성")
    void successfullyConstructMenuPrice() {
        assertDoesNotThrow(() -> new MenuPrice("1234"));
    }

    @Test
    @DisplayName("메뉴 가격 객체 생성 실패, 숫자 아님")
    void failedConstructNotNumber() {
        assertThrows(InvalidParameterTypeException.class, () -> new MenuPrice("ㅁㅇㄴㄹ"));
    }

    @Test
    @DisplayName("메뉴 가격 객체 생성 실패, 1 미만")
    void failedConstructUnderZero() {
        assertThrows(MenuPriceRangeException.class, () -> new MenuPrice("0"));
    }
}