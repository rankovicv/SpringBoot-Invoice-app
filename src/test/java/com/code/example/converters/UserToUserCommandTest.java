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
public class UserToUserCommandTest {

    public static final Long ID_VALUE = new Long(1L);
    public static final String NAME = "Test";
    public static final String LASTNAME = "Testko";

    UserToCommandUser converter;

    @Before
    public void setUp() {
        converter = new UserToCommandUser();
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new User()));
    }

    @Test
    public void testConvert() {

        User user = new User();
        user.setId(ID_VALUE);
        user.setName(NAME);
        user.setLastName(LASTNAME);

        UserCommand userCommand = converter.convert(user);

        assertEquals(ID_VALUE, userCommand.getId());
        assertEquals(NAME, userCommand.getName());
        assertEquals(LASTNAME, userCommand.getLastName());

    }

}
