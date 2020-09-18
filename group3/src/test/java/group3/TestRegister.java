package group3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import group3.persistance.User;

public class TestRegister {
    @Test
    public void emailValidate() {
        boolean result = User.isEmailValid("vxt@gmail.com");
        assertTrue(result);
    }

    @Test
    public void emailInvalidate() {
        boolean result = User.isEmailValid("abcd1234");
        assertFalse(result);
    }

    @Test
    public void emailIsEmpty() {
        final String result = User.getEmails("");
        final String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void emailIsNotEmpty() {
        final String result = User.getEmails("vxt@gmail.com");
        final String expected = "vxt@gmail.com";
        assertEquals(expected, result);
    }

    @Test
    public void passIsEmpty() {
        final String result = User.getPassword("");
        final String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void passIsNotEmpty() {
        final String result = User.getPassword("1234");
        final String expected = "1234";
        assertEquals(expected, result);
    }

    
    @Test
    public void passValidate() {
        boolean result = User.isPassValid("thanh1203");
        assertTrue(result);
    }

    @Test
    public void passInvalidate() {
        boolean result = User.isPassValid("1234");
        assertFalse(result);
    }

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
    public void IdCardIsEmpty() {
        final String result = User.getId_card("");
        final String expected = "";
        assertEquals(expected, result);
    }

    @Test
    public void IdCardIsNotEmpty() {
        final String result = User.getEmails("123456789");
        final String expected = "123456789";
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

    @Test
    public void IdCardValidate() {
        boolean result = User.isEmailValid("098765432");
        assertFalse(result);
    }

    @Test
    public void IdCardInvalidate() {
        boolean result = User.isEmailValid("abcd");
        assertFalse(result);
    }
}
