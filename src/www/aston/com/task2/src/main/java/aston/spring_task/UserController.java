package aston.spring_task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(this::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserDto dto) {
        User user = new User(dto.getName(), dto.getEmail(), dto.getAge());
        User saved = userRepository.save(user);

        return convertToDto(saved); // Используем новое имя метода
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto dto) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(dto.getName());
                    user.setEmail(dto.getEmail());
                    user.setAge(dto.getAge());
                    return ResponseEntity.ok(convertToDto(userRepository.save(user)));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    private UserDto convertToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getAge()
        );
    }
}
