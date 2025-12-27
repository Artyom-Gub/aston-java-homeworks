package aston.spring_task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class NotificationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void emailSendUserTest() throws Exception {
        mockMvc.perform(post("/api/notifications/email")
                        .param("email", "test@test.com")
                        .param("operation", "CREATE"))
                .andExpect(status().isOk())
                .andExpect(content().string("Email sent"));
    }
}