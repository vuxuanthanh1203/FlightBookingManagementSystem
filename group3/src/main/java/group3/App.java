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
        String oldPass = null;
        String newPass = null;
        String tel = null;
        String id = null;
        String address = null;
        UserUI ui = new UserUI();
        ui.loginScreen(email, pass);
        // ui.registerScreen(email, pass, name, tel, id, address);
        // ui.modifyAccount(email, name, tel, address);
        ui.changePass(email, oldPass, newPass);
    }
}
