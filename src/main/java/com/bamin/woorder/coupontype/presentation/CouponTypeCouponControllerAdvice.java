package com.bamin.woorder.coupontype.presentation;

import com.bamin.woorder.common.dto.ResponseData;
import com.bamin.woorder.common.dto.ResponseDto;
import com.bamin.woorder.common.dto.ResponseDtoStatusCode;
import com.bamin.woorder.coupontype.domain.exception.CouponTypePeriodException;
import com.bamin.woorder.coupontype.domain.exception.CouponTypeCountExcessException;
import com.bamin.woorder.coupontype.domain.exception.IllegalCouponTypeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class CouponTypeCouponControllerAdvice {

    @ExceptionHandler({CouponTypeCountExcessException.class})
    public ResponseEntity handleCountExcess(final CouponTypeCountExcessException e, final HttpServletRequest request) {
        log.error(e.getMessage());
        return ResponseEntity.status(ResponseDtoStatusCode.BAD_REQUEST)
                .body(ResponseDto.builder()
                        .path(request.getServletPath())
                        .method(request.getMethod())
                        .message(e.getMessage())
                        .data(ResponseData.builder()
                                .insert("currentCount", e.getCurrentCount())
                                .insert("requestCount", e.getRequestCount())
                                .insert("limitCount", e.getLimitCount())
                                .build())
                        .statusCode(ResponseDtoStatusCode.BAD_REQUEST)
                        .build());
    }

    @ExceptionHandler({IllegalCouponTypeException.class})
    public ResponseEntity handleCountExcess(final IllegalCouponTypeException e, final HttpServletRequest request) {
        log.error(e.getMessage());
        return ResponseEntity.status(ResponseDtoStatusCode.BAD_REQUEST)
                .body(ResponseDto.builder()
                        .path(request.getServletPath())
                        .method(request.getMethod())
                        .message(e.getMessage())
                        .data(ResponseData.builder()
                                .insert("couponNo", e.getNo())
                                .insert("couponName", e.getName())
                                .insert("couponHasCode", e.isHasCode())
                                .build())
                        .statusCode(ResponseDtoStatusCode.BAD_REQUEST)
                        .build());
    }

    @ExceptionHandler({CouponTypePeriodException.class})
    public ResponseEntity handlerCreateException(CouponTypePeriodException e, final HttpServletRequest request) {
        log.error(e.getMessage());
        return ResponseEntity.status(ResponseDtoStatusCode.BAD_REQUEST)
                .body(ResponseDto.builder()
                        .path(request.getServletPath())
                        .method(request.getMethod())
                        .message(e.getMessage())
                        .data(ResponseData.builder()
                                .insert("endTime", e.getEndTime())
                                .build())
                        .statusCode(ResponseDtoStatusCode.BAD_REQUEST)
                        .build());
    }
}
