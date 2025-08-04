package kr.kiomn2.kiomnd2splearn.adapter.webapi.dto;

import kr.kiomn2.kiomnd2splearn.domain.member.Member;

public record MemberRegisterResponse(Long memberId, String email) {

    public static MemberRegisterResponse of(Member member) {
        return new MemberRegisterResponse(member.getId(), member.getEmail().address());
    }
}
