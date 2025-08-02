package kr.kiomn2.kiomnd2splearn.application.member.required;

import com.fasterxml.jackson.databind.introspect.AnnotationCollector;
import kr.kiomn2.kiomnd2splearn.domain.member.Profile;
import kr.kiomn2.kiomnd2splearn.domain.shared.Email;
import kr.kiomn2.kiomnd2splearn.domain.member.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;

/**
 * 회원 정보를 저장하거나 조회한다
 */
public interface MemberRepository extends Repository<Member, Long> {
    Member save(Member member);

    Optional<Member> findByEmail(Email email);

    Optional<Member> findById(Long memberId);

    @Query("select m from Member m where m.memberDetail.profile = :profile")
    Optional<Member> findByProfile(Profile profile);
}
