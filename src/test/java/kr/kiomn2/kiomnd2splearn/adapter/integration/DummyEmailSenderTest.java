package kr.kiomn2.kiomnd2splearn.adapter.integration;

import kr.kiomn2.kiomnd2splearn.domain.Email;
import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.StdIo;
import org.junitpioneer.jupiter.StdOut;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DummyEmailSenderTest {

    @Test
    @StdIo
    void dummyEmailSender(StdOut out) {
        DummyEmailSender dummyEmailSender = new DummyEmailSender();

        dummyEmailSender.send(new Email("kiomnd2@test.app"), "subject", "body");

        assertThat(out.capturedLines()[0]).isEqualTo("email = Email[address=kiomnd2@test.app]");
    }
}