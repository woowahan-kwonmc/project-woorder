package com.bamin.woorder.common.utils;

import com.bamin.woorder.common.exception.InvalidParameterTypeException;

public class TypeUtils {

    public static int parseOrThrow(String value, String message) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new InvalidParameterTypeException(message);
        }
    }
}
