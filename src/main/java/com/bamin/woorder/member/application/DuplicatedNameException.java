package com.bamin.woorder.member.application;

import com.bamin.woorder.member.AbstractNameException;

public class DuplicatedNameException extends AbstractNameException {

    private static final String DUPLICATED_NAME_EXCEPTION_MESSAGE = "중복된 이름을 사용할 수 없습니다.";

    public DuplicatedNameException(String name) {
        super(DUPLICATED_NAME_EXCEPTION_MESSAGE, name);
    }
}
