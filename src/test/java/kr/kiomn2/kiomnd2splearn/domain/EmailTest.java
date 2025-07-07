package kr.kiomn2.kiomnd2splearn.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    void equalityTest() {
        var email1 = new Email("email1@email.com");
        var email2 = new Email("email1@email.com");

        assertThat(email1).isEqualTo(email2);
    }
}