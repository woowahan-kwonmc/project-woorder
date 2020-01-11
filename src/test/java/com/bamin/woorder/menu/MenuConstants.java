package com.bamin.woorder.menu;

public interface MenuConstants {

    // base path
    String MENUS_BASE_SERVLET_PATH = "/api/v1/menus";
    String SELECT_MENU_SERVLET_PATH = "/api/v1/menus/{menuNo}";
    String MENU_NO_PATH_VARIABLE_SERVLET_PATH = "/api/v1/menus/%d";

    String PAGE_QUERY_STRING = "?page=1&num=3";

    // response messages
    String MENU_CREATE_SUCCEED_RESPONSE_MESSAGE = "메뉴 등록 성공";
    String STRING_PRICE_CREATE_FAILED_RESPONSE_MESSAGE = "가격은 숫자만 입력이 가능합니다.";
    String NAME_LENGTH_CREATE_FAILED_RESPONSE_MESSAGE = "메뉴 이름은 1자 이상, 100자 이하입니다.";
    String PRICE_RANGE_CREATE_FAILED_RESPONSE_MESSAGE = "메뉴 가격은 0 이하로 설정할 수 없습니다.";
    String MENUS_SELECT_RESPONSE_MESSAGE_FORMAT = "%d 페이지의 메뉴 목록 %d개";
    String MENU_SELECT_SUCCEED_RESPONSE_MESSAGE = "메뉴 조회 성공";
    String NOT_FOUND_SELECT_FAILED_RESPONSE_MESSAGE = "해당하는 메뉴가 존재하지 않습니다.";
    String MENU_UPDATE_SUCCEED_RESPONSE_MESSAGE = "메뉴 업데이트 성공";
    String MENU_DELETE_SUCCEED_RESPONSE_MESSAGE = "메뉴 삭제 성공";

    int SELECT_PAGE = 1;
    int SELECT_NUM = 3;
    int SELECT_MENU_NO = 1;
    int NOT_EXIST_MENU_ID = 1_000_000;
}
