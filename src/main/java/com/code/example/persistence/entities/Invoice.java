package com.code.example.persistence.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by veljko on 4.8.18.
 */
@Entity
@Data
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String invoiceNumber;

    private Date time;

    private Double total;

    @ManyToOne
    private Customer customer;

}
