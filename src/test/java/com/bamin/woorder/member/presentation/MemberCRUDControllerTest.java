package com.bamin.woorder.member.presentation;

import com.bamin.woorder.AcceptanceTestUtils;
import com.bamin.woorder.TestRequestMethod;
import com.bamin.woorder.TestStatusCode;
import com.bamin.woorder.member.MemberConstants;
import com.bamin.woorder.member.dto.MemberCreateRequestDto;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class MemberCRUDControllerTest extends AcceptanceTestUtils implements MemberConstants {

    @Test
    @DisplayName("[POST] /members, 회원 가입 성공")
    void successfullyCreateUser() {
        given().
                accept(ContentType.JSON).
                contentType(ContentType.JSON).
                body(new MemberCreateRequestDto(SUCCEED_CREATE_NAME)).
        when().
                post(baseUrl(MEMBERS_SERVLET_BASE_PATH)).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(200).
                contentType(ContentType.JSON).
                body("path", is(MEMBERS_SERVLET_BASE_PATH)).
                body("method", is(TestRequestMethod.POST)).
                body("message", is(SUCCEED_CREATE_RESPONSE_MESSAGE)).
                body("data.name", is(SUCCEED_CREATE_NAME)).
                body("statusCode", is(TestStatusCode.OK)).
                body("timestamp", is(notNullValue()));
    }

    @Test
    @DisplayName("[POST] /members, 회원 가입 실패, 중복 이름")
    void failedCreateUserDuplicatedName() {
        given().
                accept(ContentType.JSON).
                contentType(ContentType.JSON).
                body(new MemberCreateRequestDto(DUPLICATED_NAME)).
        when().
                post(baseUrl(MEMBERS_SERVLET_BASE_PATH)).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(404).
                contentType(ContentType.JSON).
                body("path", is(MEMBERS_SERVLET_BASE_PATH)).
                body("method", is(TestRequestMethod.POST)).
                body("message", is(FAILED_DUPLICATED_RESPONSE_MESSAGE)).
                body("data.name", is(DUPLICATED_NAME)).
                body("statusCode", is(TestStatusCode.BAD_REQUEST)).
                body("timestamp", is(notNullValue()));
    }

    @Test
    @DisplayName("[POST] /members, 회원 가입 실패, 이름 글자 수 초과")
    void failedCreateUserNameLength() {
        given().
                accept(ContentType.JSON).
                contentType(ContentType.JSON).
                body(new MemberCreateRequestDto(UPPER_BOUND_LENGTH_NAME)).
        when().
                post(baseUrl(MEMBERS_SERVLET_BASE_PATH)).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(404).
                contentType(ContentType.JSON).
                body("path", is(MEMBERS_SERVLET_BASE_PATH)).
                body("method", is(TestRequestMethod.POST)).
                body("message", is(FAILED_LENGTH_RESPONSE_MESSAGE)).
                body("data.name", is(UPPER_BOUND_LENGTH_NAME)).
                body("statusCode", is(TestStatusCode.BAD_REQUEST)).
                body("timestamp", is(notNullValue()));
    }

    @Test
    @DisplayName("[POST] /members, 회원 가입 실패, 이름 글자 수 부족")
    void failedCreateUserNoName() {
        given().
                accept(ContentType.JSON).
                contentType(ContentType.JSON).
                body(new MemberCreateRequestDto(LOWER_BOUND_LENGTH_NAME)).
        when().
                post(baseUrl(MEMBERS_SERVLET_BASE_PATH)).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(404).
                contentType(ContentType.JSON).
                body("path", is(MEMBERS_SERVLET_BASE_PATH)).
                body("method", is(TestRequestMethod.POST)).
                body("message", is(FAILED_LENGTH_RESPONSE_MESSAGE)).
                body("data.name", is(LOWER_BOUND_LENGTH_NAME)).
                body("statusCode", is(TestStatusCode.BAD_REQUEST)).
                body("timestamp", is(notNullValue()));
    }
}