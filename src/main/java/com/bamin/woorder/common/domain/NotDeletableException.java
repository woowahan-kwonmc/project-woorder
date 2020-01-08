package com.bamin.woorder.common.domain;

public class NotDeletableException extends RuntimeException {

    private static final String NOT_DELETABLE_EXCEPTION_MESSAGE = "이미 삭제된 데이터는 삭제할 수 없습니다.";

    public NotDeletableException() {
        super(NOT_DELETABLE_EXCEPTION_MESSAGE);
    }
}
