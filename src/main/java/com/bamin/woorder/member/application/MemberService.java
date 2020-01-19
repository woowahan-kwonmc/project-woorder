package com.bamin.woorder.member.application;

import com.bamin.woorder.member.domain.Member;
import com.bamin.woorder.member.domain.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member findMemberByNo(final Long memberNo) {
        return memberRepository.findById(memberNo)
                .orElseThrow(NotFoundUserException::new);
    }

    Member createMember(final String name) {
        try {
            Member member = new Member(name);
            return memberRepository.save(member);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicatedNameException(name);
        }
    }

    Member findByMemberName(final String memberName) {
        return memberRepository.findByMemberNameMemberName(memberName)
                .orElseThrow(NotFoundUserException::new);
    }
}
