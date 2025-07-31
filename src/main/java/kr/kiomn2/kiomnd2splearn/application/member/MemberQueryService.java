package kr.kiomn2.kiomnd2splearn.application.member;

import kr.kiomn2.kiomnd2splearn.application.member.provided.MemberFinder;
import kr.kiomn2.kiomnd2splearn.application.member.required.MemberRepository;
import kr.kiomn2.kiomnd2splearn.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@RequiredArgsConstructor
@Transactional
@Validated
@Service
public class MemberQueryService implements MemberFinder {
    private final MemberRepository memberRepository;


    @Override
    public Member find(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found " + memberId));
    }
}
