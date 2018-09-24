package com.code.example.persistence.entities;

import com.code.example.util.UnitEnum;
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

    private UnitEnum unitOfMeasure;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Invoice invoice;
}