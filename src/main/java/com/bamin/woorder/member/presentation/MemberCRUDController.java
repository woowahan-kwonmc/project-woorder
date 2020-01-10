package com.bamin.woorder.member.presentation;

import com.bamin.woorder.member.application.MemberCRUDService;
import com.bamin.woorder.member.dto.MemberCreateRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberCRUDController {

    private final MemberCRUDService memberCRUDService;

    public MemberCRUDController(final MemberCRUDService memberCRUDService) {
        this.memberCRUDService = memberCRUDService;
    }

    @PostMapping("/members")
    public ResponseEntity createUser(@RequestBody MemberCreateRequestDto requestDto) {
        return ResponseEntity.ok(memberCRUDService.createMember(requestDto));
    }
}
