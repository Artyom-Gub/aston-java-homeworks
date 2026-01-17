package aston.spring_task;

import org.springframework.hateoas.RepresentationModel;

public class UserDto extends RepresentationModel<UserDto> {
    private Long id;
    private String name;
    private String email;
    private Integer age;

    public UserDto(){};
    public UserDto(Long id, String name, String email, Integer age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public Long getId() { return this.id; }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() { return this.name; }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() { return this.email; }
    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() { return this.age; }
    public void setAge(Integer age) {
        this.age = age;
    }
}