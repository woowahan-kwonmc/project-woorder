package com.bamin.woorder.menu.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MenuNameTest {

    @Test
    @DisplayName("메뉴 이름 객체 생성")
    void successfullyConstruct() {
        assertDoesNotThrow(() -> new MenuName("메뉴"));
    }

    @Test
    @DisplayName("메뉴 이름 객체 생성 실패, 글자 수 초과")
    void failedOverLengthRange() {
        assertThrows(MenuNameLengthException.class, () -> new MenuName(
                "1234567890" + "1234567890" + "1234567890" + "1234567890" +
                        "1234567890" + "1234567890" + "1234567890" + "1234567890" +
                        "1234567890" + "1234567890" + "1"));
    }

    @Test
    @DisplayName("메뉴 이름 객체 생성 실패, 글자 수 미달")
    void failedUnderLengthRange() {
        assertThrows(MenuNameLengthException.class, () -> new MenuName(""));
    }
}