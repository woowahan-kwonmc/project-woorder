package com.bamin.woorder.member.domain;

import com.bamin.woorder.common.domain.DeletableEntity;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@EqualsAndHashCode(of = "memberNo", callSuper = false)
@Table(name = "\"MEMBER\"", schema = "woorder")
@Entity
public class Member extends DeletableEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "\"member_no\"",
            updatable = false)
    private Long memberNo;

    @Embedded
    private MemberName memberName;

    @Embedded
    private MemberCouponInfos couponInfos;

    public Member(final MemberName memberName) {
        this.memberName = memberName;
    }
}
