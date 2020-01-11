package com.bamin.woorder.menu.domain;

import com.bamin.woorder.common.exception.InvalidParameterTypeException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class MenuPrice {

    private static final int MENU_PRICE_BOUND = 0;
    private static final String PRICE_NOT_NUMBER_EXCEPTION_MESSAGE = "가격은 숫자만 입력이 가능합니다.";

    @Column(name = "menu_price",
            nullable = false)
    private int menuPrice;

    public MenuPrice(final String menuPrice) {
        int price = checkMenuPriceType(menuPrice);
        checkMenuPriceRange(price);
        this.menuPrice = price;
    }

    private int checkMenuPriceType(final String menuPrice) {
        try {
            return Integer.parseInt(menuPrice);
        } catch (NumberFormatException e) {
            throw new InvalidParameterTypeException(PRICE_NOT_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    private void checkMenuPriceRange(final int price) {
        if (price <= MENU_PRICE_BOUND) {
            throw new MenuPriceRangeException(price);
        }
    }
}
