package group3;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import group3.bl.UserBL;
import group3.ui.UserUI;

public class testLogin {
    UserBL ubl = new UserBL();
    UserUI ui = new UserUI();

    @Test
    public void emailIsEmpty() {
        boolean result = ubl.login("", "");
        // final String expected = "";
        assertFalse(result);
    }
}
