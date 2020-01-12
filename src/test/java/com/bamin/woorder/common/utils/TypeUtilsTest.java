package com.bamin.woorder.common.utils;

import com.bamin.woorder.common.exception.InvalidLocalDateTimeStringException;
import com.bamin.woorder.common.exception.InvalidParameterTypeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TypeUtilsTest {

    @Test
    @DisplayName("String -> Integer 형 변환 성공")
    void successfullyParseToInteger() {
        int result = TypeUtils.parseToIntegerOrThrow("1", "메시지");
        assertThat(result).isEqualTo(1);
    }

    @Test
    @DisplayName("String -> Integer 형 변환 실패")
    void failedParseToInteger() {
        assertThrows(InvalidParameterTypeException.class,
                () -> TypeUtils.parseToIntegerOrThrow("a", "메시지"));
    }

    @Test
    @DisplayName("String -> LocalDateTime 형 변환 성공")
    void successfullyParseToTime() {
        LocalDateTime now = LocalDateTime.now();
        String nowString = now.toString();
        LocalDateTime result = TypeUtils.parseToTimeOrThrow(nowString);
        assertThat(now).isEqualTo(result);
    }

    @Test
    @DisplayName("String -> LocalDateTime 형 변환 실패")
    void failedParseToTime() {
        assertThrows(InvalidLocalDateTimeStringException.class,
                () -> TypeUtils.parseToTimeOrThrow("a"));
    }
}