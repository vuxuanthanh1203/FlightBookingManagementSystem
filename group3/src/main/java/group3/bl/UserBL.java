package group3.bl;

import group3.dal.UserDAL;

public class UserBL {
    private UserDAL uDal = new UserDAL();

    public boolean login(String email, String pass) {
        return uDal.login(email, pass);
    }

    public void register(String email, String pass, String name, String tel, String id, String address) {
        uDal.register(email, pass, name, tel, id, address);
    }

    public void modifyAccount(String email, String name, String tel, String address) {
        uDal.modifyAccount(email, name, tel, address);
    }

    public void changePassword(String oldPass, String newPass) {
        uDal.changePassword(oldPass, newPass);
    }

    public void registerPreOrder(String email, String name, String tel, String id, String address) {
        uDal.registerPreOrder(email, name, tel, id, address);
    }
}
