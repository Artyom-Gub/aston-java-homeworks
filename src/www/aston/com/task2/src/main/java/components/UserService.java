package www.aston.com.task2.src.main.java.components;

import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User create(User user) {
        return userDao.create(user);
    }

    public Optional<User> findById(Long id) {
        return userDao.findById(id);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User update(User user) {
        return userDao.update(user);
    }

    public void delete(Long id) {
        userDao.delete(id);
    }
}
