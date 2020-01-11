package com.bamin.woorder.member.application;

import com.bamin.woorder.common.dto.ResponseData;
import com.bamin.woorder.common.dto.ResponseDto;
import com.bamin.woorder.common.dto.ResponseDtoMethod;
import com.bamin.woorder.common.dto.ResponseDtoStatusCode;
import com.bamin.woorder.member.domain.Member;
import com.bamin.woorder.member.dto.MemberCreateRequestDto;
import com.bamin.woorder.member.dto.MemberReadRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberCRUDService {

    private static final String MEMBER_LOGIN_SERVLET_PATH = "/api/v1/members/login";
    private static final String MEMBER_BASE_SERVLET_PATH = "/api/v1/members";
    private static final String MEMBER_LOGIN_SUCCEED_RESPONSE_MESSAGE = "로그인 성공";
    private static final String MEMBER_CREATE_SUCCEED_RESPONSE_MESSAGE = "회원 가입 성공";
    
    private final MemberService memberService;

    @Autowired
    public MemberCRUDService(final MemberService memberService) {
        this.memberService = memberService;
    }

    public ResponseDto readMember(final MemberReadRequestDto requestDto) {
        Member savedMember = memberService.findByMemberName(requestDto.getMemberName());
        return ResponseDto.builder()
                .path(MEMBER_LOGIN_SERVLET_PATH)
                .method(ResponseDtoMethod.POST)
                .message(MEMBER_LOGIN_SUCCEED_RESPONSE_MESSAGE)
                .data(ResponseData.builder()
                        .insert("member", savedMember)
                        .build())
                .statusCode(ResponseDtoStatusCode.OK)
                .build();
    }

    public ResponseDto createMember(final MemberCreateRequestDto requestDto) {
        Member savedMember = memberService.createMember(requestDto.getName());
        return ResponseDto.builder()
                .path(MEMBER_BASE_SERVLET_PATH)
                .method(ResponseDtoMethod.POST)
                .message(MEMBER_CREATE_SUCCEED_RESPONSE_MESSAGE)
                .data(ResponseData.builder()
                        .insert("name", savedMember.getName())
                        .build())
                .statusCode(ResponseDtoStatusCode.OK)
                .build();
    }
}
