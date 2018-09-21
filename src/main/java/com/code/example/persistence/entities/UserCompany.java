package com.code.example.persistence.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * Created by veljko on 17.9.18.
 */
@Entity
@Data
public class UserCompany extends Company{

    private String bankAccount;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
