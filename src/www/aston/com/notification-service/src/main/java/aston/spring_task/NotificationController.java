package aston.spring_task;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final EmailService emailService;

    public NotificationController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/email")
    public ResponseEntity<String> sendEmailByRequest( @RequestParam String email, @RequestParam String operation) {

        String subject;
        String body;

        if ("CREATE".equals(operation)) {

            subject = "Аккаунт создан";
            body = "Аккаунт успешно создан.";

        } else if ("DELETE".equals(operation)) {

            subject = "Аккаунт удалён";
            body = "Аккаунт успешно удалён.";

        } else {
            return;
        }

        emailService.sendEmail(email, subject, body);

        return ResponseEntity.ok("Email sent");
    }
}