package kr.kiomn2.kiomnd2splearn.domain;

import lombok.Getter;

@Getter
public class Member {
    private String email;
    private String nickname;
    private String passwordHash;
    private MemberStatus status;

    public Member(String email, String nickname, String passwordHash) {
        this.email = email;
        this.nickname = nickname;
        this.passwordHash = passwordHash;
        this.status = MemberStatus.PENDING;
    }

    public void activate() {
        this.status = MemberStatus.ACTIVE;
    }

    public void deactivate() {
        this.status = MemberStatus.PENDING;
    }

}
