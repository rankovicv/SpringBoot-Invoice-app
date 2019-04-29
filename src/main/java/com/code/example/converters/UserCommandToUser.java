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
public class UserCommandToUser implements Converter<UserCommand, User> {

    @Synchronized
    @Nullable
    @Override
    public User convert(UserCommand source) {
        if(source == null) {
            return null;
        }

        final User user = new User();
        user.setId(source.getId());
        user.setName(source.getName());
        user.setLastName(source.getLastName());
        return user;
    }
}
