package com.code.example.services.impl;

import com.code.example.commands.UserCommand;
import com.code.example.converters.UserToCommandUser;
import com.code.example.exceptions.NotFoundException;
import com.code.example.persistence.entities.Role;
import com.code.example.persistence.entities.User;
import com.code.example.persistence.entities.UserCompany;
import com.code.example.persistence.entities.VerificationToken;
import com.code.example.persistence.repositories.RoleRepository;
import com.code.example.persistence.repositories.UserCompanyRepository;
import com.code.example.persistence.repositories.UserRepository;
import com.code.example.persistence.repositories.VerificationTokenRepository;
import com.code.example.services.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by veljko on 9.9.18.
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final @NonNull
    UserRepository userRepository;

    private final @NonNull
    RoleRepository roleRepository;

    private final @NonNull
    UserCompanyRepository userCompanyRepository;

    private final @NonNull
    VerificationTokenRepository tokenRepository;

    private final @NonNull
    UserToCommandUser userToCommandUser;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public static final String TOKEN_INVALID = "invalidToken";
    public static final String TOKEN_EXPIRED = "expired";
    public static final String TOKEN_VALID = "valid";

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByUsername(email);
    }

    @Override
    public UserCommand findUserById(Long userId) {

        Optional<User> userOptional = userRepository.findById(userId);

        if(!userOptional.isPresent()) {
            throw new NotFoundException("User not found");
        }

        User user = userOptional.get();

        return userToCommandUser.convert(user);
    }

    @Override
    public User saveUser(User user) {

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(false);
        Role userRole = roleRepository.findByRole("CLIENT");
        user.setRole(userRole);

        return userRepository.save(user);
    }

    @Override
    public User getUser(Long userId) {

        Optional<User> userOptional = userRepository.findById(userId);

        if (!userOptional.isPresent()) {
            throw new NotFoundException("User not found!");
        }

        return userOptional.get();
    }

    @Override
    public boolean checkPassword(long userId, String password) {

        User user = getUser(userId);

        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return true;
        }

        return false;
    }

    @Override
    public boolean changeUserPassword(String password, long userId) {

        User user = getUser(userId);

        user.setPassword(bCryptPasswordEncoder.encode(password));

        userRepository.save(user);

        return true;
    }

    @Override
    public void saveUserCommand(UserCommand userCommand) {

        Optional<User> userOptional = userRepository.findById(userCommand.getId());

        if(!userOptional.isPresent()) {
            throw new NotFoundException("User not found");
        }

        User user = userOptional.get();
        user.setName(userCommand.getName());
        user.setLastName(userCommand.getLastName());

        userRepository.save(user);

    }

    @Override
    public User getUserByVerificationToken(final String verificationToken) {
        final VerificationToken token = tokenRepository.findByToken(verificationToken);
        if (token != null) {
            return token.getUser();
        }
        return null;
    }

    @Override
    public void createVerificationTokenForUser(User user, String token) {
        final VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }

    @Override
    public VerificationToken generateNewVerificationToken(final String existingVerificationToken) {
        VerificationToken vToken = tokenRepository.findByToken(existingVerificationToken);
        vToken.updateToken(UUID.randomUUID().toString());
        vToken = tokenRepository.save(vToken);
        return vToken;
    }

    @Override
    public String validateVerificationToken(String token) {
        final VerificationToken verificationToken = tokenRepository.findByToken(token);
        if (verificationToken == null) {
            return TOKEN_INVALID;
        }

        final User user = verificationToken.getUser();
        final Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return TOKEN_EXPIRED;
        }

        user.setEnabled(true);
        tokenRepository.delete(verificationToken);
        userRepository.save(user);
        return TOKEN_VALID;
    }

    @Override
    public UserCompany saveCompany(UserCompany userCompany) {

        return userCompanyRepository.save(userCompany);
    }

    @Override
    public UserCompany getUserCompany(Long companyId) {

        return userCompanyRepository.findByUser_Id(companyId);
    }
}
