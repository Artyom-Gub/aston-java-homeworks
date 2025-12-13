package www.aston.com.task2.src.test.java.components;

import www.aston.com.task2.src.main.java.components.User;
import www.aston.com.task2.src.main.java.components.UserDao;
import www.aston.com.task2.src.main.java.components.UserService;

import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserDaoIntegrateTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

    private static UserDao userDao;

    @BeforeAll
    static void setUpAll() {
        postgres.start();

        System.setProperty("hibernate.connection.url", postgres.getJdbcUrl());
        System.setProperty("hibernate.connection.username", postgres.getUsername());
        System.setProperty("hibernate.connection.password", postgres.getPassword());
        System.setProperty("hibernate.hbm2ddl.auto", "create-drop");

        userDao = new UserDao();
    }

    @Test
    @Order(1)
    void createUserTest() {
        User user = new User("Artyom", "artyom@test.com", 30);
        User saved = userDao.create(user);

        assertNotNull(saved.getId());
        assertEquals("Artyom", saved.getName());
    }

    @Test
    @Order(2)
    void findUserByIdTest() {
        User user = userDao.create(new User("Marina", "marina@test.com", 25));
        Optional<User> found = userDao.findById(user.getId());

        assertTrue(found.isPresent());
        assertEquals("Marina", found.get().getName());
    }

    @Test
    @Order(3)
    void findAllUsersTest() {
        userDao.create(new User("Alex", "alex@test.com", 35));
        List<User> users = userDao.findAll();

        assertFalse(users.isEmpty());
    }

    @Test
    @Order(4)
    void updateUserTest() {
        User user = userDao.create(new User("Old", "old@test.com", 40));
        user.setName("New");
        userDao.update(user);

        Optional<User> updated = userDao.findById(user.getId());
        assertEquals("New", updated.get().getName());
    }

    @Test
    @Order(5)
    void deleteUserTest() {
        User user = userDao.create(new User("ToDelete", "del@test.com", 50));
        userDao.delete(user.getId());

        Optional<User> found = userDao.findById(user.getId());
        assertFalse(found.isPresent());
    }
}