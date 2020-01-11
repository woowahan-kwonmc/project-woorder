package com.bamin.woorder.menu.presentation;

import com.bamin.woorder.AcceptanceTestUtils;
import com.bamin.woorder.TestStatusCode;
import com.bamin.woorder.common.utils.easyrestassured.*;
import com.bamin.woorder.menu.dto.MenuCreateRequestDto;
import com.bamin.woorder.menu.dto.MenuRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;

class MenuCRUDControllerTest extends AcceptanceTestUtils {

    private static final String MENUS_BASE_SERVLET_PATH = "/menus";
    private static final String PAGE_QUERY_STRING = "?page=1&num=3";

    @Test
    @DisplayName("[POST] /menus, 메뉴 등록 성공")
    void successfullyCreateMenu() {
        MenuRequestDto requestDto = new MenuCreateRequestDto("치킨", "3000");

        EasyRestAssured
                .givenBody(requestDto)
                .whenRequest(baseUrl(MENUS_BASE_SERVLET_PATH), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(
                        TestStatusCode.OK, MENUS_BASE_SERVLET_PATH,
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
                .whenRequest(baseUrl(MENUS_BASE_SERVLET_PATH), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(
                        TestStatusCode.BAD_REQUEST, MENUS_BASE_SERVLET_PATH,
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
                .whenRequest(baseUrl(MENUS_BASE_SERVLET_PATH), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(
                        TestStatusCode.BAD_REQUEST, MENUS_BASE_SERVLET_PATH,
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
                .whenRequest(baseUrl(MENUS_BASE_SERVLET_PATH), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(
                        TestStatusCode.BAD_REQUEST, MENUS_BASE_SERVLET_PATH,
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
                .whenRequest(baseUrl(MENUS_BASE_SERVLET_PATH), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(
                        TestStatusCode.BAD_REQUEST, MENUS_BASE_SERVLET_PATH,
                        EasyExpectBodies.builder()
                                .insert("message", is("메뉴 가격은 0 이하로 설정할 수 없습니다."))
                                .insert("data.menuPrice", is(notNullValue()))
                );
    }

    @Test
    @DisplayName("[GET] /menus, 해당 페이지의 해당 개수만큼 메뉴 조회 성공")
    void successfullyGetPageableMenus() {
        EasyRestAssured
                .givenParams(
                        new EasyGivenQueryParameters()
                                .addParam("page", 1)
                                .addParam("num", 3))
                .whenRequest(baseUrl(MENUS_BASE_SERVLET_PATH), EasyRestAssuredRequestMethod.GET)
                .thenExpectDescriptiveWith(
                        TestStatusCode.OK, MENUS_BASE_SERVLET_PATH + PAGE_QUERY_STRING,
                        EasyExpectBodies.builder()
                                .insert("message", is(String.format("%d 페이지의 메뉴 목록 %d개", 1, 3)))
                                .insert("data.menus.size()", is(3))
                );
    }

    @Test
    @DisplayName("[GET] /menus, 쿼리 스트링에 문자가 들어가면 에러")
    void failedGetPageableMenus() {
        EasyRestAssured
                .givenParams(
                        new EasyGivenQueryParameters()
                                .addParam("page", 1)
                                .addParam("num", 3))
                .whenRequest(baseUrl(MENUS_BASE_SERVLET_PATH), EasyRestAssuredRequestMethod.GET)
                .thenExpectDescriptiveWith(
                        TestStatusCode.OK, MENUS_BASE_SERVLET_PATH + PAGE_QUERY_STRING,
                        EasyExpectBodies.builder()
                                .insert("message", is(String.format("%d 페이지의 메뉴 목록 %d개", 1, 3)))
                                .insert("data.menus.size()", is(3))
                );
    }

    @Test
    @DisplayName("[GET] /menus/{menuNo}, 특정 메뉴 상세 정보 조회 성공")
    void successfullySelectMenu() {
        EasyRestAssured
                .givenPathVariable(
                        new EasyGivenPathVariables()
                                .addVariables("menuNo", 1))
                .whenRequest(baseUrl("/menus/{menuNo}"), EasyRestAssuredRequestMethod.GET)
                .thenExpectDescriptiveWith(TestStatusCode.OK, "/menus/1",
                        EasyExpectBodies.builder()
                                .insert("message", is("메뉴 조회 성공"))
                                .insert("data.menu.menuNo", is(1))
                                .insert("data.menu.menuName", is("간장치킨"))
                                .insert("data.menu.menuPrice", is(18000)));
    }

    @Test
    @DisplayName("[GET] /menus/{menuNo}, 특정 메뉴 상세 정보 조회 실패, 존재하지 않음")
    void failedSelectMenuNotFound() {
        EasyRestAssured
                .givenPathVariable(
                        EasyGivenPathVariables.add("menuNo", 1_000_000))
                .whenRequest(baseUrl("/menus/{menuNo}"), EasyRestAssuredRequestMethod.GET)
                .thenExpectDescriptiveWith(TestStatusCode.BAD_REQUEST, "/menus/1000000",
                        EasyExpectBodies.builder()
                                .insert("message", is("해당하는 메뉴가 존재하지 않습니다."))
                                .insert("data.requestNo", is(1000000)));
    }
}