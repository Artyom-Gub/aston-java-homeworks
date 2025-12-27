package aston.spring_task;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendEmail(String email, String subject, String body) {
        System.out.println("Отправление почты до " + email);
        System.out.println("Тема: " + subject);
        System.out.println("Текст: " + body);
    }
}
