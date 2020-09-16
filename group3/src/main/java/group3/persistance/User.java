package group3.persistance;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.regex.Pattern;

import group3.dal.UserDAL;
import group3.ui.UserUI;

public class User {
    private String email;
    private String pass;
    private String name;
    private String tel;
    private int id_card;
    private String address;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getId_card() {
        return id_card;
    }

    public void setId_card(int id_card) {
        this.id_card = id_card;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static String getEmail(String email) {
        while (true) {
            System.out.print("\n- Input your email: ");
            email = getScanner().nextLine();
            if (email.isEmpty()) {
                UserUI.fieldBlank("|                 -- Email is not blank --            |");
                continue;
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

    public static String getName(String name) {
        while (true) {
            System.out.print("\n- Input your full name: ");
            name = getScanner().nextLine();
            if (name.isEmpty()) {
                UserUI.fieldBlank("|                  -- Name is not blank --            |");
                continue;
            }
            break;
        }
        return name;
    }
    
    public static String getAddress(String address) {
        while (true) {
            System.out.print("\n- Input your address: ");
            address = getScanner().nextLine();
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

    public static String getID(String idCard) {
        while (true) {
            System.out.print("\n- Input your ID card: ");
            idCard = getScanner().nextLine();
            if (idCard.isEmpty()) {
                UserUI.fieldBlank("|               -- ID Card is not blank --            |");
                continue;
            } else if (!isIDValid(idCard)) {
                UserUI.formIdCard();
                continue;
            }
            break;
        }
        return idCard;
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
