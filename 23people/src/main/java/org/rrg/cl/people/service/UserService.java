package org.rrg.cl.people.service;

import java.util.List;

import org.rrg.cl.people.entities.User;

public interface UserService {

    User save(User user);
    List<User> findAll();
    void delete(long id);
}
