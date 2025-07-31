package kr.kiomn2.kiomnd2splearn.adapter.integration;

import kr.kiomn2.kiomnd2splearn.application.member.required.EmailSender;
import kr.kiomn2.kiomnd2splearn.domain.shared.Email;
import org.springframework.context.annotation.Fallback;
import org.springframework.stereotype.Component;

@Component
@Fallback
public class DummyEmailSender implements EmailSender {

    @Override
    public void send(Email email, String subject, String body) {
        System.out.println("email = " + email);
    }
}
