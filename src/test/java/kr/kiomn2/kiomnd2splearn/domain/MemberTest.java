package kr.kiomn2.kiomnd2splearn.domain;

import kr.kiomn2.kiomnd2splearn.domain.member.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static kr.kiomn2.kiomnd2splearn.domain.MemberFixture.createMemberRegisterRequest;
import static kr.kiomn2.kiomnd2splearn.domain.MemberFixture.createPasswordEncoder;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberTest {
    Member member;
    PasswordEncoder encoder;

    @BeforeEach
    void setUp() {
        encoder = createPasswordEncoder();

        MemberRegisterRequest request = createMemberRegisterRequest();
        member = Member.register(request, encoder);
    }


    @Test
    void registerMember() {
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
        assertThat(member.getMemberDetail().getRegisteredAt()).isNotNull();
    }

    @Test
    void activate() {

        member.activate();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);
        assertThat(member.getMemberDetail().getActivatedAt()).isNotNull();
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
        assertThat(member.verifyPassword("secret123", encoder)).isTrue();
        assertThat(member.verifyPassword("hello", encoder)).isFalse();
    }



    @Test
    void changePassword() {
        member.changePassword("changesecret", encoder);
    
        assertThat(member.verifyPassword("changesecret", encoder)).isTrue();
    }

    @Test
    void shouldBeActive() {
        assertThat(member.isActive()).isFalse();

        member.activate();

        assertThat(member.isActive()).isTrue();

        member.deactivate();

        assertThat(member.isActive()).isFalse();
    }

    @Test
    void invalidEmail() {
        assertThatThrownBy(() -> {
            Member.register(createMemberRegisterRequest("invalid email"), encoder);
        }).isInstanceOf(IllegalArgumentException.class);

        Member.register(createMemberRegisterRequest(), encoder);
    }

    @Test
    void updateInfo() {
        member.activate();

        MemberInfoUpdateRequest request = new MemberInfoUpdateRequest("leo", "kiomnd100", "자기소개");
        member.updateInfo(request);

        assertThat(member.getNickname()).isEqualTo(request.nickname());
        assertThat(member.getMemberDetail().getProfile().address()).isEqualTo(request.profileAddress());
        assertThat(member.getMemberDetail().getIntroduction()).isEqualTo(request.introduction());

    }

    @Test
    void updateInfoFail() {
        assertThatThrownBy(() -> {
            var request= new MemberInfoUpdateRequest("leo", "kiomnd2", "자기소개");
            member.updateInfo(request);
        }).isInstanceOf(IllegalStateException.class);
    }
    
    @Test
    void url() {
        Profile kiond2 = new Profile("kiond2");
        assertThat(kiond2.url()).isEqualTo("@kiond2");
    }

}