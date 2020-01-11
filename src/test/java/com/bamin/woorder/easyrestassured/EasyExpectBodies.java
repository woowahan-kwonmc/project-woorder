package com.bamin.woorder.easyrestassured;

import org.hamcrest.Matcher;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class EasyExpectBodies {

    private Map<String, Matcher<?>> bodyExpects;

    private EasyExpectBodies() {
        this.bodyExpects = new LinkedHashMap<>();
    }

    public static EasyExpectBodies builder() {
        return new EasyExpectBodies();
    }

    public EasyExpectBodies insert(String jsonPath, Matcher<?> matcher) {
        this.bodyExpects.put(jsonPath, matcher);
        return this;
    }

    public EasyExpectBodies build() {
        return this;
    }

    Set<String> keySet() {
        return bodyExpects.keySet();
    }

    Matcher<?> get(String key) {
        return bodyExpects.get(key);
    }
}
