package com.bamin.woorder.common.utils;

import com.bamin.woorder.common.exception.InvalidLocalDateTimeStringException;
import com.bamin.woorder.common.exception.InvalidParameterTypeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class TypeUtils {

    private static final String CANNOT_PARSE_TO_LOCAL_DATE_TIME_EXCEPTION_MESSAGE = "시간으로 변환이 불가능한 입력입니다.";
    private static final String CHECK_QUERY_STRING_EXCEPTION_MESSAGE = "요청 쿼리 스트링을 확인해주세요.";

    public static int parseToInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new InvalidParameterTypeException(CHECK_QUERY_STRING_EXCEPTION_MESSAGE);
        }
    }

    public static int parseToIntegerOrThrow(String value, String message) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new InvalidParameterTypeException(message);
        }
    }

    public static Long parseToLong(final String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw new InvalidParameterTypeException(CHECK_QUERY_STRING_EXCEPTION_MESSAGE);
        }
    }

    public static long parseToLongOrThrow(String value, String message) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw new InvalidParameterTypeException(message);
        }
    }

    public static LocalDateTime parseToTimeOrThrow(String time) {
        try {
            return LocalDateTime.parse(time);
        } catch (DateTimeParseException e) {
            throw new InvalidLocalDateTimeStringException(CANNOT_PARSE_TO_LOCAL_DATE_TIME_EXCEPTION_MESSAGE);
        }
    }

    public static boolean parseToBooleanOrThrow(final String params, final String message) {
        if (!"true".equalsIgnoreCase(params) && !"false".equalsIgnoreCase(params)) {
            throw new InvalidParameterTypeException(message);
        }
        return Boolean.parseBoolean(params);
    }
}
