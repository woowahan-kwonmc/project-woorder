package com.bamin.woorder.member.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MemberCreateRequestDto {

    private String name;

    public MemberCreateRequestDto(final String name) {
        this.name = name;
    }
}
