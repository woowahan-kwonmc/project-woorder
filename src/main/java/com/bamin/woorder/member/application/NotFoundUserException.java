package com.bamin.woorder.member.application;

public class NotFoundUserException extends RuntimeException {

    private static final String NOT_FOUND_USER_EXCEPTION_MESSAGE = "존재하지 않는 회원입니다.";

    public NotFoundUserException() {
        super(NOT_FOUND_USER_EXCEPTION_MESSAGE);
    }
}
