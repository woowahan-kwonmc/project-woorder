package com.bamin.woorder.common.utils;

import com.bamin.woorder.common.exception.IllegalValueRangeException;

public class RangeUtils {

    private static final String ILLEGAL_VALUE_RANGE_EXCEPTION_MESSAGE = "0보다 큰 수가 필요합니다.";

    public static Long checkPositive(final Long value) {
        if (value < 1) {
            throw new IllegalValueRangeException(ILLEGAL_VALUE_RANGE_EXCEPTION_MESSAGE);
        }
        return value;
    }
}
