package com.code.example.services;

import com.code.example.persistence.entities.User;
import com.code.example.persistence.entities.UserCompany;
import com.code.example.persistence.entities.VerificationToken;

/**
 * Created by veljko on 9.9.18.
 */
public interface UserService {

    User findUserByEmail(String email);

    User saveUser(User user);

    User getUserByVerificationToken(String token);

    void createVerificationTokenForUser(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);

    VerificationToken generateNewVerificationToken(String token);

    String validateVerificationToken(String token);

    UserCompany saveCompany(UserCompany userCompany);

    UserCompany getUserCompany(Long userId);

}
