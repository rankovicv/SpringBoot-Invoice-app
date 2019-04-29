package com.code.example.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Created by veljko on 11.9.18.
 */
@Getter
@Setter
public class CurrentUser extends User {

    private long userId;
    private String name;
    private String lastName;

    public CurrentUser(String username, String password, boolean enabled, boolean accountNonExpired,
                       boolean credentialsNonExpired, boolean accountNonLocked,
                       Collection<? extends GrantedAuthority> authorities, long userId,
                       String name, String lastName) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
    }

    public static void updateUser(CurrentUser updatedUser){

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(updatedUser, updatedUser.getPassword(), updatedUser.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}