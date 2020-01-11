package com.bamin.woorder.menu.presentation;

import com.bamin.woorder.common.dto.ResponseData;
import com.bamin.woorder.common.dto.ResponseDto;
import com.bamin.woorder.menu.application.MenuNotFoundException;
import com.bamin.woorder.menu.domain.MenuNameLengthException;
import com.bamin.woorder.menu.domain.MenuPriceRangeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class MenuControllerAdvice {

    @ExceptionHandler({MenuNameLengthException.class})
    public ResponseEntity handlingNameException(final MenuNameLengthException e, final HttpServletRequest request) {
        log.error(e.getMessage());
        return ResponseEntity.status(404)
                .body(ResponseDto.builder()
                        .path(request.getServletPath())
                        .method(request.getMethod())
                        .message(e.getMessage())
                        .data(ResponseData.builder()
                                .insert("menuName", e.getMenuName())
                                .build())
                        .statusCode("404")
                        .build());
    }

    @ExceptionHandler({MenuPriceRangeException.class})
    public ResponseEntity handlingPriceException(final MenuPriceRangeException e, final HttpServletRequest request) {
        log.error(e.getMessage());
        return ResponseEntity.status(404)
                .body(ResponseDto.builder()
                        .path(request.getServletPath())
                        .method(request.getMethod())
                        .message(e.getMessage())
                        .data(ResponseData.builder()
                                .insert("menuPrice", e.getPrice())
                                .build())
                        .statusCode("404")
                        .build());
    }

    @ExceptionHandler({MenuNotFoundException.class})
    public ResponseEntity handlingSelectException(final MenuNotFoundException e, final HttpServletRequest request) {
        log.error(e.getMessage());
        return ResponseEntity.status(404)
                .body(ResponseDto.builder()
                        .path(request.getServletPath())
                        .method(request.getMethod())
                        .message(e.getMessage())
                        .data(ResponseData.builder()
                                .insert("requestNo", e.getRequestNo())
                                .build())
                        .statusCode("404")
                        .build());
    }
}
