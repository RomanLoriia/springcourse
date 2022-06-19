package ru.loriia.springcourse.dao;

import org.springframework.stereotype.Repository;
import ru.loriia.springcourse.model.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void updateUser(int id, String name, String lastName, int age) {
        User user = getUserById(id);
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);
        entityManager.merge(user);
    }

    @Override
    public void removeUser(int id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        List<User> userList = entityManager.createQuery("from User", User.class).getResultList();
        return userList;
    }
}