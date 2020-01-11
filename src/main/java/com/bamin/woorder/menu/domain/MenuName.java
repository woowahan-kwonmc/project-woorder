package com.bamin.woorder.menu.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class MenuName {

    @Column(name = "menu_name",
            length = 100)
    private String menuName;

    public MenuName(final String menuName) {
        checkNameLength(menuName);
        this.menuName = menuName;
    }

    private void checkNameLength(final String menuName) {
        if (menuName.length() < 1 || menuName.length() > 100) {
            throw new MenuNameLengthException(menuName);
        }
    }
}
