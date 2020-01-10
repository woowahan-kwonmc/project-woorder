package com.bamin.woorder.member.presentation;

import com.bamin.woorder.AcceptanceTestUtils;
import com.bamin.woorder.member.dto.MemberCreateRequestDto;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

public class MemberCRUDControllerTest extends AcceptanceTestUtils {

    @Test
    @DisplayName("[POST] /members, 회원 가입 성공")
    void successfullyCreateUser() {
        MemberCreateRequestDto requestDto = new MemberCreateRequestDto("woowaluffy");

        given().
                accept(ContentType.JSON).
                contentType(ContentType.JSON).
                body(requestDto).
        when().
                post(baseUrl("/members")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(200).
                contentType(ContentType.JSON).
                body("path", is("/members")).
                body("method", is("POST")).
                body("message", is("회원 가입 성공")).
                body("data.name", is("woowaluffy")).
                body("statusCode", is("200")).
                body("timestamp", is(notNullValue()));
    }

    @Test
    @DisplayName("[POST] /members, 회원 가입 실패, 중복 이름")
    void failedCreateUserDuplicatedName() {
        MemberCreateRequestDto requestDto = new MemberCreateRequestDto("already");

        given().
                accept(ContentType.JSON).
                contentType(ContentType.JSON).
                body(requestDto).
        when().
                post(baseUrl("/members")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(404).
                contentType(ContentType.JSON).
                body("path", is("/members")).
                body("method", is("POST")).
                body("message", is("중복된 이름을 사용할 수 없습니다.")).
                body("data.name", is("already")).
                body("statusCode", is("404")).
                body("timestamp", is(notNullValue()));
    }

    @Test
    @DisplayName("[POST] /members, 회원 가입 실패, 이름 글자 수 초과")
    void failedCreateUserNameLength() {
        MemberCreateRequestDto requestDto = new MemberCreateRequestDto("012345678901234567891");

        given().
                accept(ContentType.JSON).
                contentType(ContentType.JSON).
                body(requestDto).
        when().
                post(baseUrl("/members")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(404).
                contentType(ContentType.JSON).
                body("path", is("/members")).
                body("method", is("POST")).
                body("message", is("이름은 1자 이상, 20자 이하만 가능합니다.")).
                body("data.name", is("012345678901234567891")).
                body("statusCode", is("404")).
                body("timestamp", is(notNullValue()));
    }

    @Test
    @DisplayName("[POST] /members, 회원 가입 실패, 이름 글자 수 부족")
    void failedCreateUserNoName() {
        MemberCreateRequestDto requestDto = new MemberCreateRequestDto("");

        given().
                accept(ContentType.JSON).
                contentType(ContentType.JSON).
                body(requestDto).
        when().
                post(baseUrl("/members")).
        then().
                log().ifError().
                log().ifValidationFails().
                statusCode(404).
                contentType(ContentType.JSON).
                body("path", is("/members")).
                body("method", is("POST")).
                body("message", is("이름은 1자 이상, 20자 이하만 가능합니다.")).
                body("data.name", is("")).
                body("statusCode", is("404")).
                body("timestamp", is(notNullValue()));
    }
}