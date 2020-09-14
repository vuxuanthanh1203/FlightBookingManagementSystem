package group3;

import group3.dal.UserDAL;
import group3.ui.UserUI;

public class App {
    public static void main( String[] args )
    {
        // UserDAL.login("vxt@gmail.com", "1234");
        // UserDAL.register("", "");
        String email = null;
        String pass = null;
        UserUI ui = new UserUI();
        ui.loginScreen(email, pass);
    }
}
