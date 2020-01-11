package com.bamin.woorder.menu.presentation;

import com.bamin.woorder.AcceptanceTestUtils;
import com.bamin.woorder.common.dto.ResponseDtoStatusCode;
import com.bamin.woorder.common.utils.easyrestassured.*;
import com.bamin.woorder.menu.dto.MenuCreateRequestDto;
import com.bamin.woorder.menu.dto.MenuRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.bamin.woorder.common.dto.ResponseDtoStatusCode.NOT_FOUND;
import static com.bamin.woorder.common.dto.ResponseDtoStatusCode.OK;
import static com.bamin.woorder.menu.MenuConstants.*;
import static org.hamcrest.CoreMatchers.*;

class MenuCRUDControllerTest extends AcceptanceTestUtils {
    @Test
    @DisplayName("[POST] /menus, 메뉴 등록 성공")
    void successfullyCreateMenu() {
        MenuRequestDto requestDto = new MenuCreateRequestDto("치킨", "3000");

        EasyRestAssured
                .givenBody(requestDto)
                .whenRequest(baseUrl(MENUS_BASE_SERVLET_PATH), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(
                        OK, MENUS_BASE_SERVLET_PATH,
                        EasyExpectBodies.builder()
                                .insert("message", is(MENU_CREATE_SUCCEED_RESPONSE_MESSAGE))
                                .insert("data.menu.menuNo", is(notNullValue()))
                                .insert("data.menu.menuName", is("치킨"))
                                .insert("data.menu.menuPrice", is(3000))
                );
    }

    @Test
    @DisplayName("[POST] /menus, 메뉴 등록 실패, 문자열 가격")
    void failedCreateMenuPriceIsNotNumber() {
        MenuRequestDto requestDto = new MenuCreateRequestDto("치킨", "삼천원");

        EasyRestAssured
                .givenBody(requestDto)
                .whenRequest(baseUrl(MENUS_BASE_SERVLET_PATH), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(
                        ResponseDtoStatusCode.BAD_REQUEST, MENUS_BASE_SERVLET_PATH,
                        EasyExpectBodies.builder()
                                .insert("message", is(STRING_PRICE_CREATE_FAILED_RESPONSE_MESSAGE))
                                .insert("data", is(nullValue()))
                );
    }

    @Test
    @DisplayName("[POST] /menus, 메뉴 등록 실패, 메뉴 이름 100자 초과")
    void failedCreateMenuNameUpperBound() {
        MenuRequestDto requestDto = new MenuCreateRequestDto(
                "1234567890" + "1234567890" + "1234567890" + "1234567890" +
                        "1234567890" + "1234567890" + "1234567890" + "1234567890" +
                        "1234567890" + "1234567890" + "1", "00");

        EasyRestAssured
                .givenBody(requestDto)
                .whenRequest(baseUrl(MENUS_BASE_SERVLET_PATH), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(
                        ResponseDtoStatusCode.BAD_REQUEST, MENUS_BASE_SERVLET_PATH,
                        EasyExpectBodies.builder()
                                .insert("message", is(NAME_LENGTH_CREATE_FAILED_RESPONSE_MESSAGE))
                                .insert("data.menuName", is(notNullValue()))
                );
    }

    @Test
    @DisplayName("[POST] /menus, 메뉴 등록 실패, 메뉴 이름 1자 미만")
    void failedCreateMenuNameLowerBound() {
        MenuRequestDto requestDto = new MenuCreateRequestDto("", "200");

        EasyRestAssured
                .givenBody(requestDto)
                .whenRequest(baseUrl(MENUS_BASE_SERVLET_PATH), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(
                        ResponseDtoStatusCode.BAD_REQUEST, MENUS_BASE_SERVLET_PATH,
                        EasyExpectBodies.builder()
                                .insert("message", is(NAME_LENGTH_CREATE_FAILED_RESPONSE_MESSAGE))
                                .insert("data.menuName", is(notNullValue()))
                );
    }

    @Test
    @DisplayName("[POST] /menus, 메뉴 등록 실패, 메뉴 가격 1 미만")
    void failedCreateMenuPrice() {
        MenuRequestDto requestDto = new MenuCreateRequestDto("메뉴", "00");

        EasyRestAssured
                .givenBody(requestDto)
                .whenRequest(baseUrl(MENUS_BASE_SERVLET_PATH), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(
                        ResponseDtoStatusCode.BAD_REQUEST, MENUS_BASE_SERVLET_PATH,
                        EasyExpectBodies.builder()
                                .insert("message", is(PRICE_RANGE_CREATE_FAILED_RESPONSE_MESSAGE))
                                .insert("data.menuPrice", is(notNullValue()))
                );
    }

    @Test
    @DisplayName("[GET] /menus, 해당 페이지의 해당 개수만큼 메뉴 조회 성공")
    void successfullyGetPageableMenus() {
        EasyRestAssured
                .givenParams(
                        new EasyGivenQueryParameters()
                                .addParam("page", SELECT_PAGE)
                                .addParam("num", SELECT_NUM))
                .whenRequest(baseUrl(MENUS_BASE_SERVLET_PATH), EasyRestAssuredRequestMethod.GET)
                .thenExpectDescriptiveWith(
                        OK, MENUS_BASE_SERVLET_PATH + PAGE_QUERY_STRING,
                        EasyExpectBodies.builder()
                                .insert("message",
                                        is(String.format(MENUS_SELECT_RESPONSE_MESSAGE_FORMAT, SELECT_PAGE, SELECT_NUM)))
                                .insert("data.menus.size()", is(3))
                );
    }

    @Test
    @DisplayName("[GET] /menus, 쿼리 스트링에 문자가 들어가면 에러")
    void failedGetPageableMenus() {
        EasyRestAssured
                .givenParams(
                        new EasyGivenQueryParameters()
                                .addParam("page", SELECT_PAGE)
                                .addParam("num", SELECT_NUM))
                .whenRequest(baseUrl(MENUS_BASE_SERVLET_PATH), EasyRestAssuredRequestMethod.GET)
                .thenExpectDescriptiveWith(
                        OK, MENUS_BASE_SERVLET_PATH + PAGE_QUERY_STRING,
                        EasyExpectBodies.builder()
                                .insert("message",
                                        is(String.format(MENUS_SELECT_RESPONSE_MESSAGE_FORMAT, SELECT_PAGE, SELECT_NUM)))
                                .insert("data.menus.size()", is(3))
                );
    }

    @Test
    @DisplayName("[GET] /menus/{menuNo}, 특정 메뉴 상세 정보 조회 성공")
    void successfullySelectMenu() {
        EasyRestAssured
                .givenPathVariable(
                        new EasyGivenPathVariables()
                                .addVariables("menuNo", SELECT_MENU_NO))
                .whenRequest(baseUrl(SELECT_MENU_SERVLET_PATH), EasyRestAssuredRequestMethod.GET)
                .thenExpectDescriptiveWith(
                        OK, String.format("/menus/%d", SELECT_MENU_NO),
                        EasyExpectBodies.builder()
                                .insert("message", is(MENU_SELECT_SUCCEED_RESPONSE_MESSAGE))
                                .insert("data.menu.menuNo", is(SELECT_MENU_NO))
                                .insert("data.menu.menuName", is("간장치킨"))
                                .insert("data.menu.menuPrice", is(18000)));
    }

    @Test
    @DisplayName("[GET] /menus/{menuNo}, 특정 메뉴 상세 정보 조회 실패, 존재하지 않음")
    void failedSelectMenuNotFound() {
        EasyRestAssured
                .givenPathVariable(
                        EasyGivenPathVariables.add("menuNo", NOT_EXIST_MENU_ID))
                .whenRequest(baseUrl(SELECT_MENU_SERVLET_PATH), EasyRestAssuredRequestMethod.GET)
                .thenExpectDescriptiveWith(
                        NOT_FOUND, String.format("/menus/%d", NOT_EXIST_MENU_ID),
                        EasyExpectBodies.builder()
                                .insert("message", is(NOT_FOUND_SELECT_FAILED_RESPONSE_MESSAGE))
                                .insert("data.requestNo", is(NOT_EXIST_MENU_ID)));
    }
}