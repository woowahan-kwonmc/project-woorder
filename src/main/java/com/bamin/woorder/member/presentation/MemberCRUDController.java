package com.bamin.woorder.member.presentation;

import com.bamin.woorder.member.application.MemberCRUDService;
import com.bamin.woorder.member.domain.MemberRepository;
import com.bamin.woorder.member.dto.MemberCreateRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberCRUDController {

    private final MemberRepository memberRepository;
    private final MemberCRUDService memberCRUDService;

    public MemberCRUDController(final MemberRepository memberRepository, final MemberCRUDService memberCRUDService) {
        this.memberRepository = memberRepository;
        this.memberCRUDService = memberCRUDService;
    }

    @PostMapping("/members")
    public ResponseEntity createUser(@RequestBody MemberCreateRequestDto requestDto) {
        return ResponseEntity.ok(memberCRUDService.createMember(requestDto));
    }
}
