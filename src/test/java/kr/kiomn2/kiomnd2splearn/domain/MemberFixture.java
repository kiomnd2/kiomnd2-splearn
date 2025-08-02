package kr.kiomn2.kiomnd2splearn.domain;

import kr.kiomn2.kiomnd2splearn.domain.member.Member;
import kr.kiomn2.kiomnd2splearn.domain.member.MemberRegisterRequest;
import kr.kiomn2.kiomnd2splearn.domain.member.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

public class MemberFixture {
    public static MemberRegisterRequest createMemberRegisterRequest(String email) {
        return new MemberRegisterRequest(email, "kiomnd2", "secret123");
    }

    public static MemberRegisterRequest createMemberRegisterRequest() {
        return createMemberRegisterRequest("kiomnd2@ttt.com");
    }

    public static PasswordEncoder createPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(String password) {
                return password.toLowerCase();
            }

            @Override
            public boolean matches(String password, String passwordHash) {
                return encode(password).equals(passwordHash);
            }
        };
    }

    public static Member createMember() {
        return Member.register(createMemberRegisterRequest(), createPasswordEncoder());
    }

    public static Member createMember(Long id) {
        Member member = Member.register(createMemberRegisterRequest(), createPasswordEncoder());
        ReflectionTestUtils.setField(member, "id", id);
        return member;
    }

    public static Member createMember(String email) {
        return Member.register(createMemberRegisterRequest(email), createPasswordEncoder());
    }
}
