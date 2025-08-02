package kr.kiomn2.kiomnd2splearn.domain.member;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import kr.kiomn2.kiomnd2splearn.domain.AbstractEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.NaturalIdCache;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.springframework.util.Assert.state;

@Entity
@ToString(callSuper = true)
@Getter
@NaturalIdCache
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDetail extends AbstractEntity {
    @Embedded
    private Profile profile;

    @Column(columnDefinition = "TEXT")
    private String introduction;

    @Column(nullable = false)
    private LocalDateTime registeredAt;

    private LocalDateTime activatedAt;

    private LocalDateTime deactivatedAt;

    static MemberDetail create() {
        MemberDetail memberDetail = new MemberDetail();
        memberDetail.registeredAt = LocalDateTime.now();
        return memberDetail;
    }

    void activated() {
        Assert.isTrue(activatedAt == null, "이미 ActivatedAt은 설정되었습니다.");
        this.activatedAt = LocalDateTime.now();
    }

    void deactivated() {
        Assert.isTrue(deactivatedAt == null, "이미 deactivatedAt은 설정되었습니다.");
        this.deactivatedAt = LocalDateTime.now();
    }

    void updateInfo(MemberInfoUpdateRequest updateRequest) {
        this.profile = new Profile(updateRequest.profileAddress());
        this.introduction = Objects.requireNonNull(updateRequest.introduction());
    }
}
