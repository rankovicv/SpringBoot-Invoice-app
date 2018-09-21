package com.code.example.services.impl;

import com.code.example.converters.UserToCommandUser;
import com.code.example.persistence.entities.User;
import com.code.example.persistence.repositories.RoleRepository;
import com.code.example.persistence.repositories.UserCompanyRepository;
import com.code.example.persistence.repositories.UserRepository;
import com.code.example.persistence.repositories.VerificationTokenRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

/**
 * Created by veljko on 18.9.18.
 */
public class UserServiceImplTest {

    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    RoleRepository roleRepository;

    @Mock
    UserCompanyRepository userCompanyRepository;

    @Mock
    VerificationTokenRepository tokenRepository;

    @Mock
    UserToCommandUser userToCommandUser;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        userService = new UserServiceImpl(userRepository, roleRepository, userCompanyRepository, tokenRepository, userToCommandUser);
    }

    @Test
    public void findUserByEmail() {

        User user = new User();
        user.setId(1L);
        user.setName("Test");
        user.setUsername("test@test.com");

        when(userService.findUserByEmail(any())).thenReturn(user);

        User test = userService.findUserByEmail("test@test.com");

        assertEquals("User data should be same", user.getUsername(), test.getUsername());
        assertEquals("User id should be same", user.getId(), test.getId());
    }
}
