package com.heavy.app.repository;

import com.heavy.app.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ar4er on 17.04.16.
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addUser(User user){
        Session session = sessionFactory.openSession();
        session.save(user);
    }

    @Override
    public User get(Integer id){
        Session session = sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, id);
        return user;
    }

    @Override
    public List<User> listAll(){
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User").list();
    }

    @Override
    public void removeUser(Integer id){
        Session session = sessionFactory.getCurrentSession();
        User contact = (User) session.load(User.class,id);
        if (null!=contact){
            session.delete(contact);
        }
    }

    @Override
    public void updateUser(User user){
        Session session = sessionFactory.getCurrentSession();
        User existingUser = (User) session.get(User.class, user.getId());
        existingUser.setName(user.getName());
        existingUser.setAge(user.getAge());
        existingUser.setAdmin(user.isAdmin());
        session.save(existingUser);
    }
}
