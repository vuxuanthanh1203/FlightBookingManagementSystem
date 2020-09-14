package group3.bl;

import group3.dal.UserDAL;
import group3.persistance.ClearScreen;

public class UserBL {
    private UserDAL udal = new UserDAL();

    public  boolean login(String email, String pass) {
        ClearScreen.clear();
        if (!udal.login(email, pass)) {
            System.out.println("\n-- Account does not exist !!! --\n");
        }
        return udal.login(email, pass);
    }
}
