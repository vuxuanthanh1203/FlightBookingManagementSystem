package group3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import group3.bl.UserBL;
import group3.persistance.User;

public class TestLogin {
    UserBL ubl = new UserBL();

    @Test
    public void loginCorrect() {
        boolean result = ubl.login("vxt@gmail.com", "f942cf0d8c018a45e529eb0120c6605d");
        assertTrue(result);
    }

    @Test
    public void loginNotCorrect() {
        boolean result = ubl.login("admin", "1234");
        assertFalse(result);
    }

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
}
