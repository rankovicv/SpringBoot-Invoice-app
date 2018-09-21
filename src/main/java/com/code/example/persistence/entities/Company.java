package com.code.example.persistence.entities;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.*;

/**
 * Created by veljko on 18.9.18.
 */
@Data
@MappedSuperclass
public abstract class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min=2, max=40, message = "*Length of name must be between 2 and 40 characters")
    private String name;

    @Email(message = "*Please provide a valid Email")
    @NotEmpty(message = "*Please provide an email")
    private String email;

    private String phone;

    private String address;

    @Pattern(regexp="[\\d]{10}", message = "*PIB must be 10 dig")
    private String pib;

    private Integer idNumber;
}
