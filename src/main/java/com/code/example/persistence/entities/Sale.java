package com.code.example.persistence.entities;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by veljko on 4.8.18.
 */
@Entity
@Data
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double totalAmount;

    private Integer quantity;

    private Double price;

    private int unitOfMeasure;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Invoice invoice;
}