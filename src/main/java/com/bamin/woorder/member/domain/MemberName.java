package com.bamin.woorder.member.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
public class MemberName {

    @Column(name = "member_name",
            unique = true,
            length = 20)
    private String memberName;

    public MemberName(final String memberName) {
        checkNameLength(memberName);
        this.memberName = memberName;
    }

    private void checkNameLength(final String memberName) {
        if (memberName.length() < 1 || memberName.length() > 20) {
            throw new InvalidNameLengthException(memberName);
        }
    }
}
