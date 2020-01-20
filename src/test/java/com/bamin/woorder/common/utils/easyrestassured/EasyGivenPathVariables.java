package com.bamin.woorder.common.utils.easyrestassured;

import java.util.HashMap;
import java.util.Map;

public class EasyGivenPathVariables {

    private static EasyGivenPathVariables instance;

    private final Map<String, Object> variables;

    public EasyGivenPathVariables() {
        this.variables = new HashMap<>();
    }

    public static EasyGivenPathVariables add(String key, Object value) {
        if (instance == null) {
            instance = new EasyGivenPathVariables();
            instance.addVariables(key, value);
        }
        instance.addVariables(key, value);
        return instance;
    }

    public EasyGivenPathVariables addVariables(String key, Object value) {
        variables.put(key, value);
        return this;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }
}
