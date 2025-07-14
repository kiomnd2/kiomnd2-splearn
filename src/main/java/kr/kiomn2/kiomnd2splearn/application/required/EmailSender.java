package kr.kiomn2.kiomnd2splearn.application.required;

import kr.kiomn2.kiomnd2splearn.domain.Email;

/**
 * 이메일을 발송한다
 */
public interface EmailSender {
    void send(Email email, String subject, String body);
}
