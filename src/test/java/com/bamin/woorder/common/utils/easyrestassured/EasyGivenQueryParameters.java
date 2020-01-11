package com.bamin.woorder.common.utils.easyrestassured;

import java.util.HashMap;
import java.util.Map;

public class EasyGivenQueryParameters {

    private final Map<String, Object> params;

    public EasyGivenQueryParameters() {
        this.params = new HashMap<>();
    }

    public EasyGivenQueryParameters addParam(String key, Object value) {
        params.put(key, value);
        return this;
    }

    public Map<String, Object> getParams() {
        return params;
    }
}
