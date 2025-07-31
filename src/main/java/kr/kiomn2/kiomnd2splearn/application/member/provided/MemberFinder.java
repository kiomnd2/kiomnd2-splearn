package kr.kiomn2.kiomnd2splearn.application.member.provided;

import kr.kiomn2.kiomnd2splearn.domain.member.Member;

public interface MemberFinder {
    /**
     * 회원을 조회한다
     * @param memberId
     * @return
     */
    Member find(Long memberId);
}
