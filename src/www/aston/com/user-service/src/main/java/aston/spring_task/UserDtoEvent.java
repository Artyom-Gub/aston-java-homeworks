package aston.spring_task;

public class UserDtoEvent {
    private String email;
    private String operation;

    public UserDtoEvent() {};

    public UserDtoEvent(String operation, String email) {
        this.operation = operation;
        this.email = email;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
