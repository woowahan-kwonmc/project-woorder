package com.bamin.woorder.common.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class DescriptiveResponseDto implements DescriptiveResponse {

    private String path;
    private String method;
    private String message;
    private Object data;
    private int statusCode;
    private LocalDateTime timestamp;

    protected DescriptiveResponseDto(final String path,
                                     final String method,
                                     final Object data,
                                     final String message,
                                     final int statusCode) {
        this.path = path;
        this.method = method;
        this.data = data;
        this.message = message;
        this.statusCode = statusCode;
        this.timestamp = LocalDateTime.now();
    }
}
