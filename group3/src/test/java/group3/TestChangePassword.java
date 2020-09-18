package group3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import group3.persistance.User;

public class TestChangePassword {
    @Test
    public void oldPassIsEmpty() {
        final String result = User.getPassword("");
        final String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void oldPassIsNotEmpty() {
        final String result = User.getPassword("1234");
        final String expected = "1234";
        assertEquals(expected, result);
    }

    @Test
    public void newPassIsEmpty() {
        final String result = User.getPassword("");
        final String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void newPassIsNotEmpty() {
        final String result = User.getPassword("1234");
        final String expected = "1234";
        assertEquals(expected, result);
    }

    @Test
    public void oldPassValidate() {
        boolean result = User.isPassValid("thanh1203");
        assertTrue(result);
    }

    @Test
    public void oldPassInvalidate() {
        boolean result = User.isPassValid("1234");
        assertFalse(result);
    }

    @Test
    public void newPassValidate() {
        boolean result = User.isPassValid("thanh1203");
        assertTrue(result);
    }
}
