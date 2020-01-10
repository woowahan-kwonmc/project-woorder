package com.bamin.woorder.member;

import lombok.Getter;

@Getter
public abstract class AbstractNameException extends RuntimeException {

    protected String name;

    protected AbstractNameException(final String message, final String name) {
        super(message);
        this.name = name;
    }
}
