package kr.kiomn2.kiomnd2splearn.application;

import kr.kiomn2.kiomnd2splearn.application.required.EmailSender;
import kr.kiomn2.kiomnd2splearn.domain.Email;

import java.util.ArrayList;
import java.util.List;

class MemberServiceTest {




    static class EmailSendStub implements EmailSender {

        @Override
        public void send(Email email, String subject, String body) {

        }
    }

    static class EmailSendMock implements EmailSender {
        List<Email> tos = new ArrayList<>();

        @Override
        public void send(Email email, String subject, String body) {
            tos.add(email);
        }

        public List<Email> getTos() {
            return tos;
        }
    }
}