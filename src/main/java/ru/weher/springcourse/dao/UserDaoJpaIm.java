package ru.weher.springcourse.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.weher.springcourse.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoJpaIm implements UserDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Autowired
    public UserDaoJpaIm(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<User> listAll() {
        String jpql = "SELECT c FROM User c";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        return query.getResultList();
    }
    public void delete(int id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(int id, User updatedUser) {
        User userToBeUpdated = show(id);
        userToBeUpdated.setName(updatedUser.getName());
        userToBeUpdated.setLast_name(updatedUser.getLast_name());
        userToBeUpdated.setEmail(updatedUser.getEmail());
    }

    @Override
    public User show(int id) {
        TypedQuery<User> query = entityManager.createQuery(
                "select u from User u where u.id = :id", User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}

