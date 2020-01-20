package com.bamin.woorder.member.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberNameTest {

    @Test
    @DisplayName("MemberName 객체 생성 성공")
    void successfullyConstructMemberName() {
        assertDoesNotThrow(() -> new MemberName("1234567890"));
    }

    @Test
    @DisplayName("MemberName 객체 생성 실패, 글자수 초과")
    void failedConstructMemberNameTooLong() {
        assertThrows(InvalidNameLengthException.class, () -> new MemberName("123456789012345678901"));
    }

    @Test
    @DisplayName("MemberName 객체 생성 실패, 빈문자열")
    void failedConstructMemberNameEmpty() {
        assertThrows(InvalidNameLengthException.class, () -> new MemberName(""));
    }
}