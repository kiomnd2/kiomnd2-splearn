package kr.kiomn2.kiomnd2splearn.adapter.integration;

import kr.kiomn2.kiomnd2splearn.application.required.EmailSender;
import kr.kiomn2.kiomnd2splearn.domain.Email;
import org.springframework.stereotype.Component;

@Component
public class DummyEmailSender implements EmailSender {

    @Override
    public void send(Email email, String subject, String body) {
        System.out.println("email = " + email);
    }
}
