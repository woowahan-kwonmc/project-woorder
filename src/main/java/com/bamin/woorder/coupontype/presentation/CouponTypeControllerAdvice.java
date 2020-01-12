package com.bamin.woorder.coupontype.presentation;

import com.bamin.woorder.common.dto.ResponseData;
import com.bamin.woorder.common.dto.ResponseDto;
import com.bamin.woorder.common.dto.ResponseDtoStatusCode;
import com.bamin.woorder.coupontype.domain.exception.CouponTypeCountRangeException;
import com.bamin.woorder.coupontype.domain.exception.CouponTypeDiscountAmountException;
import com.bamin.woorder.coupontype.domain.exception.CouponTypeNameLengthException;
import com.bamin.woorder.coupontype.domain.exception.CouponTypeUsablePeriodException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class CouponTypeControllerAdvice {

    @ExceptionHandler({CouponTypeUsablePeriodException.class})
    public ResponseEntity handleUsablePeriodException(final CouponTypeUsablePeriodException e, final HttpServletRequest request) {
        log.error(e.getMessage());
        return ResponseEntity.status(ResponseDtoStatusCode.BAD_REQUEST)
                .body(ResponseDto.builder()
                        .path(request.getServletPath())
                        .method(request.getMethod())
                        .message(e.getMessage())
                        .data(ResponseData.builder()
                                .insert("currentTime", e.getCurrentTime())
                                .insert("startTime", e.getStartTime())
                                .insert("endTime", e.getEndTime())
                                .build())
                        .statusCode(ResponseDtoStatusCode.BAD_REQUEST)
                        .build());
    }

    @ExceptionHandler({CouponTypeNameLengthException.class})
    public ResponseEntity handleCouponTypeLengthException(final CouponTypeNameLengthException e, final HttpServletRequest request) {
        log.error(e.getMessage());
        return ResponseEntity.status(ResponseDtoStatusCode.BAD_REQUEST)
                .body(ResponseDto.builder()
                        .path(request.getServletPath())
                        .method(request.getMethod())
                        .message(e.getMessage())
                        .data(ResponseData.builder()
                                .insert("requestedName", e.getRequestedName())
                                .build())
                        .statusCode(ResponseDtoStatusCode.BAD_REQUEST)
                        .build());
    }

    @ExceptionHandler({CouponTypeCountRangeException.class, CouponTypeDiscountAmountException.class})
    public ResponseEntity handleCouponTypeLengthException(final RuntimeException e, final HttpServletRequest request) {
        log.error(e.getMessage());
        return ResponseEntity.status(ResponseDtoStatusCode.BAD_REQUEST)
                .body(ResponseDto.builder()
                        .path(request.getServletPath())
                        .method(request.getMethod())
                        .message(e.getMessage())
                        .statusCode(ResponseDtoStatusCode.BAD_REQUEST)
                        .build());
    }
}
