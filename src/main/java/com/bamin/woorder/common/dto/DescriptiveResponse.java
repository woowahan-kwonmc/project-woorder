package com.bamin.woorder.common.dto;

import java.time.LocalDateTime;
import java.util.Map;

public interface DescriptiveResponse {
    String getPath();

    String getMethod();

    String getMessage();

    Object getData();

    int getStatusCode();

    LocalDateTime getTimestamp();
}
