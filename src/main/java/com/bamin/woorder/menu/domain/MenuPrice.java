package com.bamin.woorder.menu.domain;

import com.bamin.woorder.common.utils.TypeUtils;
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
        int price = TypeUtils.parseOrThrow(menuPrice, PRICE_NOT_NUMBER_EXCEPTION_MESSAGE);
        checkMenuPriceRange(price);
        this.menuPrice = price;
    }

    private void checkMenuPriceRange(final int price) {
        if (price <= MENU_PRICE_BOUND) {
            throw new MenuPriceRangeException(price);
        }
    }
}
