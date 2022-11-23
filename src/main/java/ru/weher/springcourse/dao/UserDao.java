package ru.weher.springcourse.dao;
import ru.weher.springcourse.models.User;

import java.util.List;

public interface UserDao {
    List<User> listAll();
    void delete(int id);
    void save(User user);

    void update(int id, User updatedUser);

    User show(int id);
}

