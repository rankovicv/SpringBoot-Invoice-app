package com.code.example.persistence.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by veljko on 17.9.18.
 */
@Entity
@Data
public class UserCompany extends Company{

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
