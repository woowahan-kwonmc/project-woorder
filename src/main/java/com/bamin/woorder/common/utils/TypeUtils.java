package com.bamin.woorder.common.utils;

import com.bamin.woorder.common.exception.InvalidLocalDateTimeStringException;
import com.bamin.woorder.common.exception.InvalidParameterTypeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class TypeUtils {

    private static final String CANNOT_PARSE_TO_LOCAL_DATE_TIME_EXCEPTION_MESSAGE = "시간으로 변환이 불가능한 입력입니다.";

    public static int parseToIntegerOrThrow(String value, String message) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new InvalidParameterTypeException(message);
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
}
