package com.bamin.woorder.payment.presentation;

import com.bamin.woorder.common.dto.ResponseDto;
import com.bamin.woorder.common.dto.ResponseDtoStatusCode;
import com.bamin.woorder.payment.domain.exception.InvalidOrdersException;
import com.bamin.woorder.payment.domain.exception.PayMethodNotFoundException;
import com.bamin.woorder.payment.domain.exception.PaymentPriceDiscountException;
import com.bamin.woorder.payment.domain.exception.PaymentPriceRangeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class PaymentControllerAdvice {

    @ExceptionHandler({
            InvalidOrdersException.class,
            PaymentPriceDiscountException.class,
            PaymentPriceRangeException.class
    })
    public ResponseEntity handlingBadRequest(final RuntimeException e, final HttpServletRequest request) {
        log.error(e.getMessage());
        return ResponseEntity.status(ResponseDtoStatusCode.BAD_REQUEST)
                .body(ResponseDto.builder()
                        .path(request.getServletPath())
                        .method(request.getMethod())
                        .message(e.getMessage())
                        .statusCode(ResponseDtoStatusCode.BAD_REQUEST)
                        .build());
    }

    @ExceptionHandler({PayMethodNotFoundException.class})
    public ResponseEntity handlingNotFound(final RuntimeException e, final HttpServletRequest request) {
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
