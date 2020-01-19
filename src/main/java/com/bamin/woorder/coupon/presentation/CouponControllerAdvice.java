package com.bamin.woorder.coupon.presentation;

import com.bamin.woorder.common.dto.ResponseData;
import com.bamin.woorder.common.dto.ResponseDto;
import com.bamin.woorder.common.dto.ResponseDtoStatusCode;
import com.bamin.woorder.coupon.application.CouponNoNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class CouponControllerAdvice {

    @ExceptionHandler({CouponNoNotFoundException.class})
    public ResponseEntity handlingNoException(final CouponNoNotFoundException e, final HttpServletRequest request) {
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
}
