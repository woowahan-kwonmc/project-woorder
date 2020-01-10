package com.bamin.woorder.member.presentation;

import com.bamin.woorder.common.dto.ResponseData;
import com.bamin.woorder.common.dto.ResponseDto;
import com.bamin.woorder.member.AbstractNameException;
import com.bamin.woorder.member.application.DuplicatedNameException;
import com.bamin.woorder.member.application.NotFoundUserException;
import com.bamin.woorder.member.domain.InvalidNameLengthException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class MemberControllerAdvice {

    @ExceptionHandler({DuplicatedNameException.class, InvalidNameLengthException.class})
    public ResponseEntity handlingException(final AbstractNameException e, final HttpServletRequest request) {
        log.error(e.getMessage());
        return ResponseEntity.status(404)
                .body(ResponseDto.builder()
                        .path(request.getServletPath())
                        .method(request.getMethod())
                        .message(e.getMessage())
                        .data(ResponseData.builder()
                                .insert("name", e.getName())
                                .build())
                        .statusCode("404")
                        .build());
    }

    @ExceptionHandler(NotFoundUserException.class)
    public ResponseEntity handlingNotFoundUserException(final RuntimeException e, final HttpServletRequest request) {
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
