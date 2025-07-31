package kr.kiomn2.kiomnd2splearn.domain.member;

import jakarta.persistence.*;
import kr.kiomn2.kiomnd2splearn.domain.AbstractEntity;
import kr.kiomn2.kiomnd2splearn.domain.shared.Email;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

import java.util.Objects;

import static org.springframework.util.Assert.state;

@Entity
@ToString(callSuper = true, exclude = "memberDetail")
@Getter
@NaturalIdCache
public class Member extends AbstractEntity {

    @NaturalId
    private Email email;

    private String nickname;

    private String passwordHash;

    private MemberStatus status;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private MemberDetail memberDetail;

    protected Member() {}

    public static Member register(MemberRegisterRequest createRequest, PasswordEncoder passwordEncoder) {
//        return new MemberBuilder()
//                .email(createRequest.email())
//                .nickname(createRequest.nickname())
//                .passwordHash(createRequest.password())
//                .build();

        Member member = new Member();
        member.email = new Email(createRequest.email());
        member.nickname = Objects.requireNonNull(createRequest.nickname());
        member.passwordHash = Objects.requireNonNull(passwordEncoder.encode(createRequest.password()));

        member.status = MemberStatus.PENDING;

        member.memberDetail = MemberDetail.create();

        return member;
    }

    public void activate() {
        state(status == MemberStatus.PENDING, "PENDING 상태가 아닙니다.");

        this.status = MemberStatus.ACTIVE;
        this.memberDetail.activated();
    }

    public void deactivate() {
        state(status == MemberStatus.ACTIVE, "ACTIVE 상태가 아닙니다.");

        this.status = MemberStatus.DEACTIVATED;
        this.memberDetail.deactivated();
    }

    public boolean verifyPassword(String password, PasswordEncoder encoder) {
        return encoder.matches(password, this.passwordHash);
    }

    public void changeNickname(String nickname) {
        this.nickname = Objects.requireNonNull(nickname);
    }

    public void updateInfo(MemberInfoUpdateRequest updateRequest) {
        this.nickname = Objects.requireNonNull(updateRequest.nickname());

        this.memberDetail.updateInfo(updateRequest);
    }

    public void changePassword(String password, PasswordEncoder encoder) {
        this.passwordHash = encoder.encode(Objects.requireNonNull(password));
    }

    public boolean isActive() {
        return getStatus() == MemberStatus.ACTIVE;
    }
}
