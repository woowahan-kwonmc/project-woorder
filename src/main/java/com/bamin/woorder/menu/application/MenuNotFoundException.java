package com.bamin.woorder.menu.application;

import lombok.Getter;

@Getter
public class MenuNotFoundException extends RuntimeException {

    private static final String NOT_FOUND_MENU_EXCEPTION_MESSAGE = "해당하는 메뉴가 존재하지 않습니다.";

    private Long requestNo;

    MenuNotFoundException(final Long requestNo) {
        super(NOT_FOUND_MENU_EXCEPTION_MESSAGE);
        this.requestNo = requestNo;
    }
}
