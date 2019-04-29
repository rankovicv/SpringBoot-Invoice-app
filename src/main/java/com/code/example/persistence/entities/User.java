package com.code.example.persistence.entities;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Set;

/**
 * Created by veljko on 9.9.18.
 */
@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String username;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])[\\s\\S\\d]{8,}", message = "Must be a little complicated")
    @NotEmpty(message = "*Please provide your password")
    private String password;

    @NotEmpty(message = "*Please provide your name")
    private String name;

    @NotEmpty(message = "*Please provide your last name")
    private String lastName;

    private boolean enabled;

    @ManyToOne()
    private Role role;

    public User() {
        super();
        this.enabled=false;
    }
}
