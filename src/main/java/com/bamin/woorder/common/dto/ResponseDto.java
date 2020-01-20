package com.bamin.woorder.common.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class ResponseDto extends DescriptiveResponseDto {

    private ResponseDto() {
        super();
    }

    @Builder
    public ResponseDto(final String path,
                       final String method,
                       final Object data,
                       final String message,
                       final int statusCode) {
        super(path, method, data, message, statusCode);
    }
}
