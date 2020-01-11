package com.bamin.woorder.menu.domain;

import lombok.Getter;

@Getter
public class MenuPriceRangeException extends RuntimeException {

    public static final String MENU_PRICE_RANGE_EXCEPTION = "메뉴 가격은 0 이하로 설정할 수 없습니다.";

    private int price;

    MenuPriceRangeException(final int price) {
        super(MENU_PRICE_RANGE_EXCEPTION);
        this.price = price;
    }
}
