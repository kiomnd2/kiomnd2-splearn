package kr.kiomn2.kiomnd2splearn.domain.member;

public record MemberInfoUpdateRequest(
        String nickname,
        String profileAddress,
        String introduction) {
}
