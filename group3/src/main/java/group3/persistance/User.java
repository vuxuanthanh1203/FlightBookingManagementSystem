package group3.persistance;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.regex.Pattern;

import group3.dal.UserDAL;
import group3.ui.MenuUI;
import group3.ui.UserUI;

public class User {
    private static String email = "";
    private static String pass = "";
    private static String name = "";
    private static String tel = "";
    private static String id_card = "";
    private static String address = "";

    public static String getEmails(String email) {
        String user_email = email;
        return user_email;
    }

    public void setEmail(String email) {
        User.email = email;
    }

    public static String getPassword(String pass) {
        String user_pass = pass;
        return user_pass;
    }

    public void setPass(String pass) {
        User.pass = pass;
    }
    public static String getNames(String name) {
        String user_name = name;
        return user_name;
    }

    public void setName(String name) {
        User.name = name;
    }

    public static String getTels(String tel) {
        String user_tel = tel;
        return user_tel;
    }

    public void setTel(String tel) {
        User.tel = tel;
    }

    public static String getId_card(String id_card) {
        String user_id_card = id_card;
        return user_id_card;
    }

    public void setId_card(String id_card) {
        User.id_card = id_card;
    }

    public static String getAdd(String address) {
        String user_address = address;
        return user_address;
    }

    public void setAddress(String address) {
        User.address = address;
    }

    public static String getEmail(String email) {
        while (true) {
            System.out.print("\n- Input your email: ");
            email = getScanner().nextLine();
            if (email.isEmpty()) {
                UserUI.fieldBlank("|                 -- Email is not blank --            |");
                continue;
            } else if (email.equals("3")) {
                UserUI.registerScreen(email, pass, name, tel, id_card, address);
            }else if (email.equals("0")) {
                MenuUI.menu();
            } else if (!isEmailValid(email)) {
                UserUI.formEmail();
                continue;
            }
            break;
        }
        return email;
    }

    public static String checkEmail(String email) {
        while (true) {
            System.out.print("\n- Input your email: ");
            email = getScanner().nextLine();
            if (email.isEmpty()) {
                UserUI.fieldBlank("|                 -- Email is not blank --            |");
                continue;
            } else if (!isEmailValid(email)) {
                UserUI.formEmail();
                continue;
            } else if (UserDAL.checkEmail(email)) {
                System.out.println("\n-- Email Already Exists !!! --\n");
                continue;
            }
            break;
        }
        return email;
    }

    public static String getPass(String pass) {
        while (true) {
            pass = getMd5(PasswordField.readPassword(""));
            if (pass.isEmpty()) {
                UserUI.fieldBlank("|              -- Password is not blank --            |");
                continue;
            } else if (!isPassValid(pass)) {
                UserUI.formPass();
                continue;
            }
            break;
        }
        return pass;
    }

    public static String checkPass(String pass) {
        while (true) {
            pass = getMd5(PasswordField.readPassword(""));
            if (pass.isEmpty()) {
                UserUI.fieldBlank("|              -- Password is not blank --            |");
                continue;
            } else if (!isPassValid(pass)) {
                UserUI.formPass();
                continue;
            } else if (pass.equals("cfcd208495d565ef66e7dff9f98764da") ) {
                ClearScreen.clear();
                MenuUI.cusScreen();
            }
            break;
        }
        return pass;
    }

    public static String getName(String name) {
        while (true) {
            System.out.print("\n- Input your full name: ");
            name = getScanner().nextLine().toUpperCase();
            if (name.isEmpty()) {
                UserUI.fieldBlank("|                  -- Name is not blank --            |");
                continue;
            } else if (name.equalsIgnoreCase("back")) {
                ClearScreen.clear();
                MenuUI.cusScreen();
            }
            break;
        }
        return name;
    }

    public static String getAddress(String address) {
        while (true) {
            System.out.print("\n- Input your address: ");
            address = getScanner().nextLine().toUpperCase();
            if (address.isEmpty()) {
                UserUI.fieldBlank("|               -- Address is not blank --            |");
                continue;
            }
            break;
        }
        return address;
    }

    public static String getTel(String tel) {
        while (true) {
            System.out.print("\n- Input your tel: ");
            tel = getScanner().nextLine();
            if (tel.isEmpty()) {
                UserUI.fieldBlank("|                   -- Tel is not blank --            |");
                continue;
            } else if (!isTelValid(tel)) {
                UserUI.formTel();
                continue;
            }
            break;
        }
        return tel;
    }

    public static String getID(String id_card) {
        while (true) {
            System.out.print("\n- Input your ID card: ");
            id_card = getScanner().nextLine();
            if (id_card.isEmpty()) {
                UserUI.fieldBlank("|               -- ID Card is not blank --            |");
                continue;
            } else if (!isIDValid(id_card)) {
                UserUI.formIdCard();
                continue;
            }
            break;
        }
        return id_card;
    }

    public static String getMd5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
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

    public static boolean isTelValid(String tel) {
        final Pattern TEL_REGEX = Pattern.compile("0[0-9]{9}", Pattern.CASE_INSENSITIVE);
        return TEL_REGEX.matcher(tel).matches();
    }

    public static boolean isIDValid(String idCard) {
        final Pattern ID_REGEX = Pattern.compile("[0-9]{9}", Pattern.CASE_INSENSITIVE);
        return ID_REGEX.matcher(idCard).matches();
    }

    public static Scanner getScanner() {
        return new Scanner(System.in);
    }
}
