package com.code.example.commands;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * Created by veljko on 21.9.18.
 */
@Setter
@Getter
@NoArgsConstructor
public class UserCommand {

    private Long id;

    @NotEmpty(message = "*Please provide your name")
    private String name;

    @NotEmpty(message = "*Please provide your last name")
    private String lastName;
}
