package com.bamin.woorder.common.dto;

import lombok.Getter;
import org.springframework.util.Assert;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
public class ResponseData {

    private Map<String, Object> data;

    private ResponseData() {
        this.data = new LinkedHashMap<>();
    }

    public static ResponseData builder() {
        return new ResponseData();
    }

    public ResponseData insert(String key, Object value) {
        Assert.notNull(key, "'key' must not be null");
        Assert.notNull(value, "'value' must not be null");
        data.put(key, value);
        return this;
    }

    public Map<String, Object> build() {
        return this.getData();
    }
}
