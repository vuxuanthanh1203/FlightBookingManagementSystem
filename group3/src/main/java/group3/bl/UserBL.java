package group3.bl;

import group3.dal.UserDAL;

public class UserBL {
    private UserDAL udal = new UserDAL();

    public boolean login(String email, String pass) {
        return udal.login(email, pass);
    }

    public void register(String email, String pass, String name, String tel, String id, String address) {
        udal.register(email, pass, name, tel, id, address);
    }

    public void modifyAccount(String email, String name, String tel, String address) {
        udal.modifyAccount(email, name, tel, address);
    }

    public void changePassword(String email, String oldPass, String newPass) {
        udal.changePassword(email, oldPass, newPass);
    }
}
