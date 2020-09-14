package group3.bl;

import group3.dal.UserDAL;

public class UserBL {
    private UserDAL udal = new UserDAL();

    public boolean login(String email, String pass) {
        return udal.login(email, pass);
    }

    public void register(String email, String pass) {
        udal.register(email, pass);
    }
}
