package aston.spring_task;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserDtoEventListener {

    private final EmailService emailService;

    public UserDtoEventListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @KafkaListener(topics = "user-events", groupId = "notification-group")
    public void handleUserEvent(UserDtoEvent event) {
        String subject;
        String body;

        if ("CREATE".equals(event.getOperation())) {

            subject = "Аккаунт создан";
            body = "Аккаунт успешно создан.";

        } else if ("DELETE".equals(event.getOperation())) {

            subject = "Аккаунт удалён";
            body = "Аккаунт успешно удалён.";

        } else {
            return;
        }

        emailService.sendEmail(event.getEmail(), subject, body);
    }
}
