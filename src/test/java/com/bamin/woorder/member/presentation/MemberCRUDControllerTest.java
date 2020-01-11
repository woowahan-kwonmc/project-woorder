package com.bamin.woorder.member.presentation;

import com.bamin.woorder.AcceptanceTestUtils;
import com.bamin.woorder.common.utils.easyrestassured.EasyExpectBodies;
import com.bamin.woorder.common.utils.easyrestassured.EasyRestAssured;
import com.bamin.woorder.common.utils.easyrestassured.EasyRestAssuredRequestMethod;
import com.bamin.woorder.member.dto.MemberCreateRequestDto;
import com.bamin.woorder.member.dto.MemberReadRequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.bamin.woorder.common.dto.ResponseDtoStatusCode.*;
import static com.bamin.woorder.member.MemberConstants.*;
import static org.hamcrest.CoreMatchers.*;

public class MemberCRUDControllerTest extends AcceptanceTestUtils {

    @Test
    @DisplayName("[POST] /members, 회원 가입 성공")
    void successfullyCreateUser() {
        EasyRestAssured
                .givenBody(new MemberCreateRequestDto(SUCCEED_CREATE_NAME))
                .whenRequest(baseUrl(MEMBERS_BASE_SERVLET_PATH), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(OK, MEMBERS_BASE_SERVLET_PATH,
                        EasyExpectBodies.builder()
                                .insert("message", is(SUCCEED_CREATE_RESPONSE_MESSAGE))
                                .insert("data.name", is(SUCCEED_CREATE_NAME))
                                .build());
    }

    @Test
    @DisplayName("[POST] /members, 회원 가입 실패, 중복 이름")
    void failedCreateUserDuplicatedName() {
        EasyRestAssured
                .givenBody(new MemberCreateRequestDto(ALREADY_SAVED_NAME))
                .whenRequest(baseUrl(MEMBERS_BASE_SERVLET_PATH), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(BAD_REQUEST, MEMBERS_BASE_SERVLET_PATH,
                        EasyExpectBodies.builder()
                                .insert("message", is(FAILED_DUPLICATED_RESPONSE_MESSAGE))
                                .insert("data.name", is(ALREADY_SAVED_NAME))
                                .build());
    }

    @Test
    @DisplayName("[POST] /members, 회원 가입 실패, 이름 글자 수 초과")
    void failedCreateUserNameLength() {
        EasyRestAssured
                .givenBody(new MemberCreateRequestDto(UPPER_BOUND_LENGTH_NAME))
                .whenRequest(baseUrl(MEMBERS_BASE_SERVLET_PATH), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(BAD_REQUEST, MEMBERS_BASE_SERVLET_PATH,
                        EasyExpectBodies.builder()
                                .insert("message", is(FAILED_LENGTH_RESPONSE_MESSAGE))
                                .insert("data.name", is(UPPER_BOUND_LENGTH_NAME))
                                .build());
    }

    @Test
    @DisplayName("[POST] /members, 회원 가입 실패, 이름 글자 수 부족")
    void failedCreateUserNoName() {
        EasyRestAssured
                .givenBody(new MemberCreateRequestDto(LOWER_BOUND_LENGTH_NAME))
                .whenRequest(baseUrl(MEMBERS_BASE_SERVLET_PATH), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(BAD_REQUEST, MEMBERS_BASE_SERVLET_PATH,
                        EasyExpectBodies.builder()
                                .insert("message", is(FAILED_LENGTH_RESPONSE_MESSAGE))
                                .insert("data.name", is(LOWER_BOUND_LENGTH_NAME))
                                .build());
    }

    @Test
    @DisplayName("[POST] /members/login, 회원 정보 읽기 성공")
    void successfullyLogin() {
        EasyRestAssured
                .givenBody(new MemberReadRequestDto(ALREADY_SAVED_NAME))
                .whenRequest(baseUrl(MEMBERS_LOGIN_SERVLET_PATH), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(OK, MEMBERS_LOGIN_SERVLET_PATH,
                        EasyExpectBodies.builder()
                                .insert("message", is(SUCCEED_LOGIN_MESSAGE))
                                .insert("data.member", is(notNullValue()))
                                .build());
    }

    @Test
    @DisplayName("[POST] /members/login, 회원 정보 읽기 실패, 존재하지 않음")
    void failedLoginNotExist() {
        EasyRestAssured
                .givenBody(new MemberReadRequestDto(NOT_FOUND_NAME))
                .whenRequest(baseUrl(MEMBERS_LOGIN_SERVLET_PATH), EasyRestAssuredRequestMethod.POST)
                .thenExpectDescriptiveWith(NOT_FOUND, MEMBERS_LOGIN_SERVLET_PATH,
                        EasyExpectBodies.builder()
                                .insert("message", is(NOT_FOUND_MEMBER_RESPONSE_MESSAGE))
                                .insert("data.member", is(nullValue()))
                                .build());
    }
}