package com.code.example.services.impl;

import com.code.example.persistence.entities.Role;
import com.code.example.persistence.entities.User;
import com.code.example.persistence.repositories.RoleRepository;
import com.code.example.persistence.repositories.UserRepository;
import com.code.example.services.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;

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

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByUsername(email);
    }

    @Override
    public void saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = roleRepository.findByRole("CLIENT");
        user.setRole(userRole);
        userRepository.save(user);
    }
}
