package com.code.example.services;

import com.code.example.persistence.entities.User;

/**
 * Created by veljko on 9.9.18.
 */
public interface UserService {

    User findUserByEmail(String email);

    void saveUser(User user);

}
