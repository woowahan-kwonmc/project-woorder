package com.bamin.woorder.member.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
class MemberName {

    @Column(name = "\"member_name\"",
            length = 20)
    private String memberName;

    public MemberName(final String memberName) {
        this.memberName = memberName;
    }
}
