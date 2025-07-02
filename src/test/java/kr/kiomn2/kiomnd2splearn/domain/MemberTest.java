package kr.kiomn2.kiomnd2splearn.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberTest {

    @Test
    void createMember() {
        var member = new Member("kiomnd2@ttt.com", "kiomnd2", "secret");

        assertThat(member.getStatus()).isEqualTo(MemberStatus.PENDING);
    }
}