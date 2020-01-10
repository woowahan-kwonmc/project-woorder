package com.bamin.woorder.member.domain;

import com.bamin.woorder.member.AbstractNameException;

public class InvalidNameLengthException extends AbstractNameException {

    private static final String INVALID_NAME_LENGTH_EXCEPTION_MESSAGE = "이름은 1자 이상, 20자 이하만 가능합니다.";

    public InvalidNameLengthException(final String name) {
        super(INVALID_NAME_LENGTH_EXCEPTION_MESSAGE, name);
    }
}
