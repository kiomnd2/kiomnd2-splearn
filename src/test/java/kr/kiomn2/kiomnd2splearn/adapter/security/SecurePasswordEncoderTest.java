package kr.kiomn2.kiomnd2splearn.adapter.security;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SecurePasswordEncoderTest {

    @Test
    void securityPasswordEncoder() {
        SecurePasswordEncoder securePasswordEncoder = new SecurePasswordEncoder();

        String passwordHash = securePasswordEncoder.encode("secret");

        assertThat(securePasswordEncoder.matches("secret", passwordHash)).isTrue();
        assertThat(securePasswordEncoder.matches("wrong", passwordHash)).isFalse();
    }
}