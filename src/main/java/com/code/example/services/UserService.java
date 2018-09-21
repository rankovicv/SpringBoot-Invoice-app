package com.code.example.services;

import com.code.example.commands.UserCommand;
import com.code.example.persistence.entities.User;
import com.code.example.persistence.entities.UserCompany;
import com.code.example.persistence.entities.VerificationToken;

/**
 * Created by veljko on 9.9.18.
 */
public interface UserService {

    User findUserByEmail(String email);

    UserCommand findUserById(Long userId);

    User saveUser(User user);

    User getUser(Long userId);

    boolean checkPassword(long userId, String password);

    boolean changeUserPassword(String password, long userId);

    void saveUserCommand(UserCommand userCommand);

    User getUserByVerificationToken(String token);

    void createVerificationTokenForUser(User user, String token);

    VerificationToken getVerificationToken(String VerificationToken);

    VerificationToken generateNewVerificationToken(String token);

    String validateVerificationToken(String token);

    UserCompany saveCompany(UserCompany userCompany);

    UserCompany getUserCompany(Long userId);

}
