package com.bamin.woorder.menu.presentation;

import com.bamin.woorder.AcceptanceTestUtils;
import com.bamin.woorder.TestStatusCode;
import com.bamin.woorder.common.utils.easyrestassured.EasyExpectBodies;
import com.bamin.woorder.common.utils.easyrestassured.EasyRestAssured;
import com.bamin.woorder.common.utils.easyrestassured.EasyRestAssuredRequestMethod;
import com.bamin.woorder.menu.dto.MenuCreateRequestDto;
import com.bamin.woorder.menu.dto.MenuRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;

class MenuCRUDControllerTest extends AcceptanceTestUtils {

    private static final String MENUS_BASE_SERVLET_PATH = "/menus";

    @Test
    @DisplayName("[POST] /menus, 메뉴 등록 성공")
    void successfullyCreateMenu() {
        MenuRequestDto requestDto = new MenuCreateRequestDto("치킨", "3000");

        EasyRestAssured
                .givenBody(requestDto)
                .whenRequest(
                        baseUrl(MENUS_BASE_SERVLET_PATH),
                        EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(
                        TestStatusCode.OK,
                        MENUS_BASE_SERVLET_PATH,
                        EasyExpectBodies.builder()
                                .insert("message", is("메뉴 등록 성공"))
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
                .whenRequest(
                        baseUrl(MENUS_BASE_SERVLET_PATH),
                        EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(
                        TestStatusCode.BAD_REQUEST,
                        MENUS_BASE_SERVLET_PATH,
                        EasyExpectBodies.builder()
                                .insert("message", is("가격은 숫자만 입력이 가능합니다."))
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
                .whenRequest(
                        baseUrl(MENUS_BASE_SERVLET_PATH),
                        EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(
                        TestStatusCode.BAD_REQUEST,
                        MENUS_BASE_SERVLET_PATH,
                        EasyExpectBodies.builder()
                                .insert("message", is("메뉴 이름은 1자 이상, 100자 이하입니다."))
                                .insert("data.menuName", is(notNullValue()))
                );
    }

    @Test
    @DisplayName("[POST] /menus, 메뉴 등록 실패, 메뉴 이름 1자 미만")
    void failedCreateMenuNameLowerBound() {
        MenuRequestDto requestDto = new MenuCreateRequestDto("", "200");

        EasyRestAssured
                .givenBody(requestDto)
                .whenRequest(
                        baseUrl(MENUS_BASE_SERVLET_PATH),
                        EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(
                        TestStatusCode.BAD_REQUEST,
                        MENUS_BASE_SERVLET_PATH,
                        EasyExpectBodies.builder()
                                .insert("message", is("메뉴 이름은 1자 이상, 100자 이하입니다."))
                                .insert("data.menuName", is(notNullValue()))
                );
    }

    @Test
    @DisplayName("[POST] /menus, 메뉴 등록 실패, 메뉴 가격 1 미만")
    void failedCreateMenuPrice() {
        MenuRequestDto requestDto = new MenuCreateRequestDto("메뉴", "00");

        EasyRestAssured
                .givenBody(requestDto)
                .whenRequest(
                        baseUrl(MENUS_BASE_SERVLET_PATH),
                        EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(
                        TestStatusCode.BAD_REQUEST,
                        MENUS_BASE_SERVLET_PATH,
                        EasyExpectBodies.builder()
                                .insert("message", is("메뉴 가격은 0 이하로 설정할 수 없습니다."))
                                .insert("data.menuPrice", is(notNullValue()))
                );
    }
}