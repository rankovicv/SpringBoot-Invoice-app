package com.code.example.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by veljko on 4.8.18.
 */
@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

//    private Integer quantity;
//
//    private Double price;
}

