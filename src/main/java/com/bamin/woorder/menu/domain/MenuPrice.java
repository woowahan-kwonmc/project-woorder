package com.bamin.woorder.menu.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class MenuPrice {

    @Column(name = "menu_price",
            nullable = false)
    private int menuPrice;

    public MenuPrice(final int menuPrice) {
        this.menuPrice = menuPrice;
    }
}
