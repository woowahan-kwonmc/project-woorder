package com.bamin.woorder.common.exception;

public class ParameterConditionException extends RuntimeException {
    public ParameterConditionException() {
    }

    public ParameterConditionException(final String message) {
        super(message);
    }

    public ParameterConditionException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ParameterConditionException(final Throwable cause) {
        super(cause);
    }

    public ParameterConditionException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
