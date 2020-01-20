package com.bamin.woorder.member.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MemberReadRequestDto {

    public String memberName;

    public MemberReadRequestDto(final String memberName) {
        this.memberName = memberName;
    }
}
