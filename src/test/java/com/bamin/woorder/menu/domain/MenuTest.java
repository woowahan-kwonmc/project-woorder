package com.bamin.woorder.menu.domain;

import com.bamin.woorder.common.exception.InvalidParameterTypeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MenuTest {

    private Menu menu;

    @BeforeEach
    void setUp() {
        menu = new Menu("김치찌개", "12000");
    }

    @Test
    @DisplayName("메뉴 이름 업데이트 성공")
    void updateName() {
        menu.updateName("부대찌개");
        assertThat(menu.getName()).isEqualTo("부대찌개");
    }

    @Test
    @DisplayName("메뉴 이름 업데이트 실패, 빈 문자열")
    void updateNameFailed() {
        assertThrows(MenuNameLengthException.class, () -> menu.updateName(""));
    }

    @Test
    @DisplayName("메뉴 가격 업데이트 성공")
    void updatePrice() {
        menu.updatePrice("12000");
        assertThat(menu.getPrice()).isEqualTo(12000);
    }

    @Test
    @DisplayName("메뉴 가격 업데이트 성공, 가격에 문자 전달시")
    void updatePriceFailed() {
        assertThrows(InvalidParameterTypeException.class, () -> menu.updatePrice("rrr"));
    }
}