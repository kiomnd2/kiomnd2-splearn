package kr.kiomn2.kiomnd2splearn.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class MemberTest {
    Member member;
    PasswordEncoder encoder;

    @BeforeEach
    void setUp() {
        encoder = new PasswordEncoder() {
            @Override
            public String encode(String password) {
                return password.toLowerCase();
            }

            @Override
            public boolean matches(String password, String passwordHash) {
                return encode(password).equals(passwordHash);
            }
        };
        member = Member.create("kiomnd2@ttt.com", "kiomnd2", "secret", encoder);
    }

    @Test
    void createMember() {
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }

    @Test
    void activate() {

        member.activate();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);
    }

    @Test
    void activateFail() {

        member.activate();

        assertThatThrownBy(member::activate)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void deactivate() {

        member.activate();

        member.deactivate();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.DEACTIVATED);
    }

    @Test
    void deactivateFail() {

        assertThatThrownBy(member::deactivate)
                .isInstanceOf(IllegalStateException.class);

        member.activate();
        member.deactivate();

        assertThatThrownBy(member::deactivate)
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void verifyPassword() {
        assertThat(member.verifyPassword("secret", encoder)).isTrue();
        assertThat(member.verifyPassword("hello", encoder)).isFalse();
    }

    @Test
    void changeNickname() {
        assertThat(member.getNickname()).isEqualTo("kiomnd2");

        member.changeNickname("kio");

        assertThat(member.getNickname()).isEqualTo("kio");
    }

    @Test
    void changePassword() {
        member.changePassword("changesecret");
    
        assertThat(member.verifyPassword("changesecret", encoder)).isTrue();
    }
}