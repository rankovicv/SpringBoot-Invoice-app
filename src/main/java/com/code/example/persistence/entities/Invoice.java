package com.code.example.persistence.entities;

import com.code.example.util.CurrencyEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @NotNull
    private String invoiceNumber;

    private Date time;

    private Double total;

    private CurrencyEnum currency;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    public Invoice() {
        super();
        this.currency = CurrencyEnum.EUR;
    }

}
