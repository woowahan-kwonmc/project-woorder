package com.bamin.woorder.menu.domain;

import lombok.Getter;

@Getter
public class MenuNameLengthException extends RuntimeException {

    private static final String MENU_NAME_LENGTH_EXCEPTION_MESSAGE = "메뉴 이름은 1자 이상, 100자 이하입니다.";

    private String menuName;

    MenuNameLengthException(String menuName) {
        super(MENU_NAME_LENGTH_EXCEPTION_MESSAGE);
        this.menuName = menuName;
    }
}
