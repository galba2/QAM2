package model;

import org.junit.jupiter.api.Test;

import DBAccess.UserAttemptQuery;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class UserAttemptTest {

    @Test
    void getBoolean() throws SQLException {
        UserAttempt u = new UserAttempt("test");
        assertEquals(null, u.getIsUserAttemptSuccessful());
    }

    @Test
    void getUName() throws SQLException {
        UserAttempt u = new UserAttempt("test");
        assertEquals("test", u.getUserName());
    }

    @Test
    void getPassword() throws SQLException {
        UserAttempt u = new UserAttempt("test");
        assertEquals("password", u.getPassword());
    }
}