package com.code.example.converters;

import com.code.example.commands.UserCommand;
import com.code.example.persistence.entities.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by veljko on 21.9.18.
 */
public class UserCommandToUserTest {

    public static final Long ID_VALUE = new Long(1L);
    public static final String NAME = "Test";
    public static final String LASTNAME = "Testko";

    UserCommandToUser converter;

    @Before
    public void setUp() {
        converter = new UserCommandToUser();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new UserCommand()));
    }

    @Test
    public void testConvert() {

        UserCommand userCommand = new UserCommand();
        userCommand.setId(ID_VALUE);
        userCommand.setName(NAME);
        userCommand.setLastName(LASTNAME);

        User user = converter.convert(userCommand);

        assertEquals(ID_VALUE, user.getId());
        assertEquals(NAME, user.getName());
        assertEquals(LASTNAME, user.getLastName());

    }

}
