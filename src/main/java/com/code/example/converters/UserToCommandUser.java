package com.code.example.converters;

import com.code.example.commands.UserCommand;
import com.code.example.persistence.entities.User;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;


/**
 * Created by veljko on 21.9.18.
 */
@Component
public class UserToCommandUser implements Converter<User, UserCommand> {

    @Synchronized
    @Nullable
    @Override
    public UserCommand convert(User source) {
        if (source == null) {
            return null;
        }

        final UserCommand userCommand = new UserCommand();

        userCommand.setId(source.getId());
        userCommand.setLastName(source.getLastName());
        userCommand.setName(source.getName());

        return userCommand;
    }
}
