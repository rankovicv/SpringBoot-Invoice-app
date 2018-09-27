package com.code.example.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by veljko on 4.8.18.
 */
@Entity
@Data
public class Customer extends Company {

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;
}

