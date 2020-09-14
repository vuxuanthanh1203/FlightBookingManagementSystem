package group3.persistance;

import java.util.Scanner;
import java.util.regex.Pattern;

import group3.ui.UserUI;

public class Model {
    public static String getEmail(String email) {
        while (true) {
            System.out.print("\n- Input your email: ");
            email = getScanner().nextLine();
            if (email.isEmpty()) {
                UserUI.fieldBlank("   -- Email");
                continue;
            } else if (!isEmailValid(email)) {
                UserUI.formEmail();
                continue;
            }
            break;
        }
        return email;
    }

    public static String getPass(String pass) {
        while (true) {
            System.out.print("\n- Input your password: ");
            pass = getScanner().nextLine();
            if (pass.isEmpty()) {
                UserUI.fieldBlank("-- Password");
                continue;
            } else if (!isPassValid(pass)) {
                UserUI.formPass();
                continue;
            }
            break;
        }
        return pass;
    }

    public static boolean isEmailValid(String email) {
        final Pattern EMAIL_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
                Pattern.CASE_INSENSITIVE);
        return EMAIL_REGEX.matcher(email).matches();
    }

    public static boolean isPassValid(String pass) {
        final Pattern PASS_REGEX = Pattern.compile("(?=.*[0-9])(?=.*[a-z]).{8,}", Pattern.CASE_INSENSITIVE);
        return PASS_REGEX.matcher(pass).matches();
    }

    public static Scanner getScanner() {
        return new Scanner(System.in);
    }
}
