package kr.kiomn2.kiomnd2splearn.application.member.provided;

import jakarta.persistence.EntityManager;
import kr.kiomn2.kiomnd2splearn.domain.member.Member;
import kr.kiomn2.kiomnd2splearn.domain.MemberFixture;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Import(SplearnTestConfiguration.class)
@Transactional
@SpringBootTest
record MemberFinderTest(MemberFinder memberFinder, MemberRegister memberRegister, EntityManager entityManager) {

    @Test
    void find() {
        Member member = memberRegister.register(MemberFixture.createMemberRegisterRequest());

        entityManager.flush();
        entityManager.clear();

        Member found = memberFinder.find(member.getId());
        
        assertThat(member.getId()).isEqualTo(found.getId());
    }

}