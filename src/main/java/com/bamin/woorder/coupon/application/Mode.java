package com.bamin.woorder.coupon.application;

import lombok.Getter;

@Getter
public enum Mode {
    DOWNLOAD("다운로드", "/downloadMode"),
    CODE("코드", "/codeMode");

    private String name;
    private String path;

    Mode(final String name, final String path) {
        this.name = name;
        this.path = path;
    }
}
