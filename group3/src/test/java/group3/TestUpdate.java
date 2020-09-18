package group3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import group3.persistance.User;

public class TestUpdate {
    @Test
    public void nameIsEmpty() {
        final String result = User.getNames("");
        final String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void nameIsNotEmpty() {
        final String result = User.getNames("thanh");
        final String expected = "thanh";
        assertEquals(expected, result);
    }
    
    @Test
    public void telIsEmpty() {
        final String result = User.getTels("");
        final String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void telIsNotEmpty() {
        final String result = User.getTels("0987654321");
        final String expected = "0987654321";
        assertEquals(expected, result);
    }

    @Test
    public void addressIsEmpty() {
        final String result = User.getAdd("");
        final String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void addressIsNotEmpty() {
        final String result = User.getAdd("TB");
        final String expected = "TB";
        assertEquals(expected, result);
    }

    @Test
    public void telValidate() {
        boolean result = User.isTelValid("0987654321");
        assertTrue(result);
    }

    @Test
    public void telInvalidate() {
        boolean result = User.isEmailValid("abcd1234");
        assertFalse(result);
    }
}
