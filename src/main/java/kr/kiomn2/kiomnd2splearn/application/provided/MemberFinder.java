package kr.kiomn2.kiomnd2splearn.application.provided;

import kr.kiomn2.kiomnd2splearn.domain.Member;

public interface MemberFinder {
    /**
     * 회원을 조회한다
     * @param memberId
     * @return
     */
    Member find(Long memberId);
}
