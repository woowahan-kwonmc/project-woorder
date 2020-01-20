package com.bamin.woorder.order.presentation;

import com.bamin.woorder.common.dto.ResponseData;
import com.bamin.woorder.common.dto.ResponseDto;
import com.bamin.woorder.common.dto.ResponseDtoStatusCode;
import com.bamin.woorder.order.OrderException;
import com.bamin.woorder.order.application.NotFoundOrderException;
import com.bamin.woorder.order.domain.AlreadyCanceledOrderException;
import com.bamin.woorder.order.domain.OrderAlreadyOrderPaymentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class OrderControllerAdvice {

    @ExceptionHandler({OrderException.class, OrderAlreadyOrderPaymentException.class})
    public ResponseEntity handlingOrderException(final RuntimeException e, final HttpServletRequest request) {
        log.error(e.getMessage());
        return ResponseEntity.status(ResponseDtoStatusCode.BAD_REQUEST)
                .body(ResponseDto.builder()
                        .path(request.getServletPath())
                        .method(request.getMethod())
                        .message(e.getMessage())
                        .statusCode(ResponseDtoStatusCode.BAD_REQUEST)
                        .build());
    }

    @ExceptionHandler({NotFoundOrderException.class})
    public ResponseEntity handlingNotFound(final NotFoundOrderException e, final HttpServletRequest request) {
        log.error(e.getMessage());
        return ResponseEntity.status(ResponseDtoStatusCode.NOT_FOUND)
                .body(ResponseDto.builder()
                        .path(request.getServletPath())
                        .method(request.getMethod())
                        .message(e.getMessage())
                        .data(ResponseData.builder()
                                .insert("requestNo", e.getRequestNo())
                                .build())
                        .statusCode(ResponseDtoStatusCode.NOT_FOUND)
                        .build());
    }

    @ExceptionHandler({AlreadyCanceledOrderException.class})
    public ResponseEntity handlingBasicException(final AlreadyCanceledOrderException e, final HttpServletRequest request) {
        log.error(e.getMessage());
        return ResponseEntity.status(ResponseDtoStatusCode.NOT_FOUND)
                .body(ResponseDto.builder()
                        .path(request.getServletPath())
                        .method(request.getMethod())
                        .message(e.getMessage())
                        .statusCode(ResponseDtoStatusCode.NOT_FOUND)
                        .build());
    }
}
