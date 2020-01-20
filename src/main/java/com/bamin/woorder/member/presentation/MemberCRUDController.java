package com.bamin.woorder.member.presentation;

import com.bamin.woorder.member.application.MemberCRUDService;
import com.bamin.woorder.member.dto.MemberCreateRequestDto;
import com.bamin.woorder.member.dto.MemberReadRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MemberCRUDController {

    private final MemberCRUDService memberCRUDService;

    public MemberCRUDController(final MemberCRUDService memberCRUDService) {
        this.memberCRUDService = memberCRUDService;
    }

    @PostMapping("/members/login")
    public ResponseEntity readUser(@RequestBody MemberReadRequestDto requestDto) {
        return ResponseEntity.ok(memberCRUDService.readMember(requestDto));
    }

    @PostMapping("/members")
    public ResponseEntity createUser(@RequestBody MemberCreateRequestDto requestDto) {
        return ResponseEntity.ok(memberCRUDService.createMember(requestDto));
    }
}
