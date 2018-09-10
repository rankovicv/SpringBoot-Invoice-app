package com.code.example.persistence.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by veljko on 9.9.18.
 */
@Data
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String role;
}