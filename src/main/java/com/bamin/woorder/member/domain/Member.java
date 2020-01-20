package com.bamin.woorder.member.domain;

import com.bamin.woorder.common.domain.DeletableEntity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(of = "memberNo", callSuper = false)
@Table(name = "MEMBER")
@Entity
public class Member extends DeletableEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_no",
            updatable = false)
    private Long memberNo;

    @Embedded
    private MemberName memberName;

    public Member(final String name) {
        this.memberName = new MemberName(name);
    }

    public String getName() {
        return memberName.getMemberName();
    }
}
