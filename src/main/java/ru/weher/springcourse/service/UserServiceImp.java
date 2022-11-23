package ru.weher.springcourse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.weher.springcourse.dao.UserDao;
import ru.weher.springcourse.models.User;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService {

    private final UserDao userDao;

    @Autowired
    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> listAll() {
        return userDao.listAll();
    }

    @Transactional
    public void delete(int id) {
        userDao.delete(id);
    }

    @Transactional
    public void save(User user) {
        userDao.save(user);
    }

    @Transactional
    public void update(int id, User updatedUser) {
        userDao.update(id, updatedUser);
    }

    public User show(int id) {
        return userDao.show(id);
    }

}
