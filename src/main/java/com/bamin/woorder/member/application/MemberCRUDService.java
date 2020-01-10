package com.bamin.woorder.member.application;

import com.bamin.woorder.common.dto.ResponseData;
import com.bamin.woorder.common.dto.ResponseDto;
import com.bamin.woorder.member.domain.Member;
import com.bamin.woorder.member.dto.MemberCreateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberCRUDService {

    private final MemberService memberService;

    @Autowired
    public MemberCRUDService(final MemberService memberService) {
        this.memberService = memberService;
    }

    public ResponseDto createMember(final MemberCreateRequestDto requestDto) {
        Member savedMember = memberService.createMember(requestDto.getName());
        return ResponseDto.builder()
                .path("/members")
                .method("POST")
                .message("회원 가입 성공")
                .data(ResponseData.builder()
                        .insert("name", savedMember.getName())
                        .build())
                .statusCode("200")
                .build();
    }
}
