package kr.kiomn2.kiomnd2splearn.application.member.provided;


import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintViolationException;
import kr.kiomn2.kiomnd2splearn.domain.MemberFixture;
import kr.kiomn2.kiomnd2splearn.domain.member.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@Import(SplearnTestConfiguration.class)
@SpringBootTest
record MemberRegisterTest(MemberRegister memberRegister, EntityManager entityManager) {

    @Test
    void register() {
        MemberRegisterRequest memberRegisterRequest = MemberFixture.createMemberRegisterRequest();
        Member member = memberRegister.register(memberRegisterRequest);

        System.out.println("member = " + member);

        assertThat(member.getId()).isNotNull();
        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }

    @Test
    void duplicateEmailFail() {
        memberRegister.register(MemberFixture.createMemberRegisterRequest());

        assertThatThrownBy(() -> memberRegister.register(MemberFixture.createMemberRegisterRequest()))
                .isInstanceOf(DuplicateEmailException.class);

    }

    @Test
    void activate() {
        Member member = getMember();

        member = memberRegister.activate(member.getId());

        entityManager.flush();

        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);
        assertThat(member.getMemberDetail().getActivatedAt()).isNotNull();
    }

    @Test
    void deactivate() {
        Member member = getMember();

        member = memberRegister.activate(member.getId());
        entityManager.flush();
        entityManager.clear();

        member = memberRegister.deactivate(member.getId());

        assertThat(member.getStatus()).isEqualTo(MemberStatus.DEACTIVATED);
        assertThat(member.getMemberDetail().getDeactivatedAt()).isNotNull();
    }

    @Test
    void updateInfo() {
        Member member = getMember();

        member = memberRegister.activate(member.getId());
        entityManager.flush();
        entityManager.clear();

        MemberInfoUpdateRequest request = new MemberInfoUpdateRequest("leo123", "kiomnd100", "자기소개");

        member = memberRegister.updateInfo(member.getId(), request);

        assertThat(member.getStatus()).isEqualTo(MemberStatus.ACTIVE);
    }

    @Test
    void updateInfoFail() {
        Member member = getMember();
        member = memberRegister.activate(member.getId());
        MemberInfoUpdateRequest request = new MemberInfoUpdateRequest("leo123", "md222", "자기소개");
        member = memberRegister.updateInfo(member.getId(), request);


        Member member2 = getMember("kiomnd2@splearn.com");
        memberRegister.activate(member2.getId());
        entityManager.flush();
        entityManager.clear();

        // member2는 기존의 member 와 같은 profile 을 사용할 수 없다
        assertThatThrownBy(() -> {
            memberRegister.updateInfo(member2.getId(), new MemberInfoUpdateRequest("kiomnd2", "md222", "자기소개"));
        }).isInstanceOf(DuplicateProfileException.class);

        memberRegister.updateInfo(member2.getId(), new MemberInfoUpdateRequest("kiomnd2", "md223", "자깃괘"));
    }


    @Test
    void memberRegisterRequestFail() {
        var invalid = new MemberRegisterRequest("kiond2@splearn.com", "ki2", "secret123");
//        memberRegister.register(invalid);
        assertThatThrownBy(() -> memberRegister.register(invalid))
                .isInstanceOf(ConstraintViolationException.class);
    }

    private Member getMember() {
        Member member = memberRegister.register(MemberFixture.createMemberRegisterRequest());
        entityManager.flush();
        entityManager.clear();
        return member;
    }

    private Member getMember(String email) {
        Member member = memberRegister.register(MemberFixture.createMemberRegisterRequest(email));
        entityManager.flush();
        entityManager.clear();
        return member;
    }
}
