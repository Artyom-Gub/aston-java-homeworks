package www.aston.com.task2.src.test.java.components;

import www.aston.com.task2.src.main.java.components.User;
import www.aston.com.task2.src.main.java.components.UserDao;
import www.aston.com.task2.src.main.java.components.UserService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest { // Добавлена пропущенная строка объявления класса

    @Mock
    private UserDao userDao;

    @InjectMocks
    private UserService userService;

    @Test
    void createUserTest() {
        User input = new User("Artyom", "artyom@test.com", 20);
        User saved = new User("Artyom", "artyom@test.com", 20);
        saved.setId(1L);

        when(userDao.create(input)).thenReturn(saved);

        User result = userService.create(input);

        assertNotNull(result.getId());
        assertEquals("Artyom", result.getName());

        verify(userDao).create(input);
    }

    @Test
    void findUserByIdTest() {
        User mockUser = new User("Masha", "masha@test.com", 22);
        mockUser.setId(1L);

        when(userDao.findById(1L)).thenReturn(Optional.of(mockUser));

        Optional<User> result = userService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("Masha", result.get().getName());
        verify(userDao).findById(1L);
    }

    @Test
    void findAllUsersTest() {
        User user = new User("Users", "users@test.com", 30);
        when(userDao.findAll()).thenReturn(List.of(user));

        List<User> result = userService.findAll();

        assertEquals(1, result.size());
        verify(userDao).findAll();
    }

    @Test
    void updateUserTest() {
        User updated = new User("Updated", "up@test.com", 25);
        updated.setId(1L);
        when(userDao.update(updated)).thenReturn(updated);

        User result = userService.update(updated);

        assertEquals("Updated", result.getName());
        verify(userDao).update(updated);
    }

    @Test
    void deleteUserTest() {
        doNothing().when(userDao).delete(1L);

        userService.delete(1L);

        verify(userDao).delete(1L);
    }
}