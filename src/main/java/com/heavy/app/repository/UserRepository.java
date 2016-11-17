package com.heavy.app.repository;

import com.heavy.app.domain.User;

import java.util.List;

/**
 * Created by dimas on 17.11.16.
 */
public interface UserRepository {
    public void addUser(User user);

    public User get(Integer id);

    public List<User> listAll();

    public void removeUser(Integer id);

    public void updateUser(User user);

    }

