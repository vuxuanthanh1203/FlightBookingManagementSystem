package group3.ui;

import java.util.Scanner;

import group3.bl.UserBL;
import group3.persistance.ClearScreen;
import group3.persistance.Model;

public class UserUI {

    UserBL ubl = new UserBL();

    public void loginScreen(String email, String pass) {
        ClearScreen.clear();
        // while (true) {
            System.out.println("=====================================================================");
            System.out.println("|                                LOGIN                              |");
            System.out.println("+-------------------------------------------------------------------+");
            email = Model.getEmail(email);
            pass = Model.getPass(pass);
            ubl.login(email, pass);
        // }
    }

    public void registerScreen() {
        while (true) {
            System.out.println("=====================================================================");
            System.out.println("|                           CREATE NEW ACCOUNT                      |");
            System.out.println("+-------------------------------------------------------------------+");
            System.out.print("- Input your email (email@domain.com): ");
            String email = getScanner().nextLine();
            System.out.print("- Input your password: ");
            String pass = getScanner().nextLine();

        }
    }

    public static void fieldBlank(String field) {
        System.out.println("\n+-----------------------------------------------------+");
        System.out.println("|              " + field + " is not blank --            |");
        System.out.println("+-----------------------------------------------------+");
    }

    public static void formEmail() {
        System.out.println("\n+-----------------------------------------+");
        System.out.println("|          -- Invalid email !!! --        |");
        System.out.println("+-----------------------------------------+");
        System.out.println("|(!) Email format: email@domain.com       |");
        System.out.println("+-----------------------------------------+\n");
    }

    public static void formPass() {
        System.out.println("\n+-----------------------------------------------------+");
        System.out.println("|                -- Invalid Password --               |");
        System.out.println("+-----------------------------------------------------+");
        System.out.println("|(!) at least 8 characters                            |");
        System.out.println("|(!) a digit must occur at least once                 |");
        System.out.println("|(!) a lower case letter must occur at least once     |");
        System.out.println("+-----------------------------------------------------+\n");
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
