package group3.ui;

import java.util.Scanner;

import group3.bl.UserBL;
import group3.dal.UserDAL;
import group3.persistance.ClearScreen;
import group3.persistance.PasswordField;
import group3.persistance.User;

public class UserUI {
    UserBL ubl = new UserBL();

    public void loginScreen(String email, String pass) {
        ClearScreen.clear();
        System.out.println("=====================================================================");
        System.out.println("|                                LOGIN                              |");
        System.out.println("+-------------------------------------------------------------------+");
        email = User.getEmail(email);
        System.out.print("\n- Input your password: ");
        pass = User.getPass(pass);
        ubl.login(email, pass);
    }

    public void registerScreen(String email, String pass, String name, String tel, String id, String address) {
        ClearScreen.clear();
        System.out.println("=====================================================================");
        System.out.println("|                           CREATE NEW ACCOUNT                      |");
        System.out.println("+-------------------------------------------------------------------+");
        email = User.checkEmail(email);
        System.out.print("\n- Input your password: ");
        pass = User.getPass(pass);
        String password = User.getMd5(PasswordField.readPassword("\n- Confirm password: "));
        name = User.getName(name);
        tel = User.getTel(tel);
        id = User.getID(id);
        address = User.getAddress(address);

        if (pass.equals(password)) {
            ubl.register(email, pass, name, tel, id, address);
        } else {
            System.out.println("\nIncorrect password !");
            System.out.println("\nRegister failed !");
        }
    }

    public void modifyAccount(String email, String name, String tel, String address) {
        ClearScreen.clear();
        selectAccount(email);
        System.out.println("\n=====================================================================");
        System.out.println("|                            MODIFY ACCOUNT                         |");
        System.out.println("+-------------------------------------------------------------------+");
        name = User.getName(name);
        tel = User.getTel(tel);
        address = User.getAddress(address);
        ubl.modifyAccount(email, name, tel, address);
    }

    public void selectAccount(String email) {
        ClearScreen.clear();
        System.out.println("=====================================================================");
        System.out.println("|                          ACCOUNT INFORMATION                      |");
        System.out.println("+-------------------------------------------------------------------+\n");
        UserDAL.selectAccount(email);
        System.out.println("+-------------------------------------------------------------------+\n");
    }

    public void changePass(String email, String oldPass, String newPass) {
        ClearScreen.clear();
        while (true) {

            System.out.println("\n=====================================================================");
            System.out.println("|                            CHANGE PASSWORD                        |");
            System.out.println("+-------------------------------------------------------------------+");
            System.out.print("\n- Input your password: ");
            oldPass = User.getPass(oldPass);
            if (!UserDAL.checkPass(oldPass)) {
                System.out.println("\n(!) Incorrect Password !");
            } else {
                System.out.print("\n- Input new password: ");
                newPass = User.getPass(newPass);
                if (newPass.equals(oldPass)) {
                    formChangePass();
                } else {
                    ubl.changePassword(email, oldPass, newPass);
                }
            }
        }
    }

    public static void fieldBlank(String field) {
        ClearScreen.clear();
        System.out.println("\n+-----------------------------------------------------+");
        System.out.println(field);
        System.out.println("+-----------------------------------------------------+");
    }

    public static void formEmail() {
        ClearScreen.clear();
        System.out.println("\n+-----------------------------------------+");
        System.out.println("|          -- Invalid email !!! --        |");
        System.out.println("+-----------------------------------------+");
        System.out.println("|(!) Email format: email@domain.com       |");
        System.out.println("+-----------------------------------------+\n");
    }

    public static void formTel() {
        ClearScreen.clear();
        System.out.println("\n+-----------------------------------------+");
        System.out.println("|            -- Invalid tel !!! --         |");
        System.out.println("+-----------------------------------------+");
        System.out.println("|(!) Tel has 10 digits beginning with 0   |");
        System.out.println("+-----------------------------------------+\n");
    }

    public static void formIdCard() {
        ClearScreen.clear();
        System.out.println("\n+-----------------------------------------+");
        System.out.println("|            -- Invalid Id card !!! --       |");
        System.out.println("+-----------------------------------------+");
        System.out.println("|(!) Id card has 9 digits                 |");
        System.out.println("+-----------------------------------------+\n");
    }

    public static void formPass() {
        ClearScreen.clear();
        System.out.println("\n+-----------------------------------------------------+");
        System.out.println("|                -- Invalid Password --               |");
        System.out.println("+-----------------------------------------------------+");
        System.out.println("|(!) At least 8 characters                            |");
        System.out.println("|(!) A digit must occur at least once                 |");
        System.out.println("|(!) A lower case letter must occur at least once     |");
        System.out.println("+-----------------------------------------------------+\n");
    }

    public static void formChangePass() {
        ClearScreen.clear();
        System.out.println("\n+---------------------------------------------------------------+");
        System.out.println("|                      -- Change Password --                    |");
        System.out.println("+---------------------------------------------------------------+");
        System.out.println("|(!) New password must be different from your previous password |");
        System.out.println("|(!) Password change failed                                     |");
        System.out.println("+---------------------------------------------------------------+\n");
    }

    public static void adminScreen() {
        System.out.println("This is admin screen");
    }

    public static void customerScreen() {
        System.out.println("This is customer screen");
    }

    public static Scanner getScanner() {
        return new Scanner(System.in);
    }
}
