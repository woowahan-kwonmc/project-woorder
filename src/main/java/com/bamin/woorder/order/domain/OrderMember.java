package com.bamin.woorder.order.domain;

import com.bamin.woorder.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Embeddable
class OrderMember {

    @OneToOne
    @JoinColumn(name = "order_member_no")
    private Member member;

    OrderMember(final Member member) {
        this.member = member;
    }

    Long getMemberNo() {
        return member.getMemberNo();
    }
}
