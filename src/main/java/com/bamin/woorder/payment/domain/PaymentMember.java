package com.bamin.woorder.payment.domain;


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
class PaymentMember {

    @OneToOne
    @JoinColumn(name = "payment_member_no")
    private Member member;

    PaymentMember(final Member member) {
        this.member = member;
    }
}
