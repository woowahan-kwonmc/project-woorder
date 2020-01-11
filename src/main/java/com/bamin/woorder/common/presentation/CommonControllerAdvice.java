package com.bamin.woorder.common.presentation;

import com.bamin.woorder.common.dto.ResponseDto;
import com.bamin.woorder.common.exception.InvalidParameterTypeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class CommonControllerAdvice {

    @ExceptionHandler({InvalidParameterTypeException.class})
    public ResponseEntity handlingException(final RuntimeException e, final HttpServletRequest request) {
        log.error(e.getMessage());
        return ResponseEntity.status(404)
                .body(ResponseDto.builder()
                        .path(request.getServletPath())
                        .method(request.getMethod())
                        .message(e.getMessage())
                        .statusCode("404")
                        .build());
    }
}
