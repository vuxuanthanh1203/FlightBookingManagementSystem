package group3.ui;

import java.util.Scanner;

import group3.bl.BookingBL;
import group3.bl.UserBL;
import group3.dal.UserDAL;
import group3.persistance.ClearScreen;
import group3.ui.Header;

public class MenuUI {
    static int choice = 0;
    static String email = "";
    static String name = "";
    static String tel = "";
    static String address = "";
    static int quantity = 0;
    static String bookingDate = "";
    static double totalCost = 0;
    static int flightID = 0;
    static int bookingID = 0;
    static String pass = "";
    static String newPass = "";

    static UserUI userUI = new UserUI();
    static BookingUI bookingUI = new BookingUI();

    public static void cusScreen() {
        Header.header();
        System.out.println("     Welcome " + UserDAL.user_name + " To Flight Booking Management System !");
        while (true) {
            System.out.println("\n=====================================================================");
            System.out.println("|                                MENU                               |");
            System.out.println("+-------------------------------------------------------------------+");
            System.out.println("|                                                                   |");
            System.out.println("| 1. Modify Account                                                 |");
            System.out.println("|                                                                   |");
            System.out.println("| 2. Book Flight                                                    |");
            System.out.println("|                                                                   |");
            System.out.println("| 3. Manage Booking                                                 |");
            System.out.println("|                                                                   |");
            System.out.println("| 4. Change Password                                                |");
            System.out.println("|                                                                   |");
            System.out.println("| 5. Logout                                                         |");
            System.out.println("|                                                                   |");
            System.out.println("+-------------------------------------------------------------------+\n");

            System.out.print("Choice your action: ");
            choice = getScanner().nextInt();
            switch (choice) {
                case 1:
                    userUI.modifyAccount(email, name, tel, address);
                    break;
                case 2:
                    FlightUI.chooseSchedule();
                    bookingUI.booking(quantity, bookingDate, totalCost, UserDAL.user_id, flightID);
                    break;
                case 3:
                    manageBooking();
                    break;
                case 4:
                    userUI.changePass(email, pass, newPass);
                    break;
                case 5:
                    UserDAL.resetKey();
                    menu();
                    break;
                default:
                    System.out.println("\n-- Function does not exist ! --");
                    break;
            }
        }
    }

    public static void manageBooking() {
        while (true) {
            ClearScreen.clear();
            Header.header();
            System.out.println("\n=====================================================================");
            System.out.println("|                             MANAGE BOOKING                        |");
            System.out.println("+-------------------------------------------------------------------+");
            System.out.println("|                                                                   |");
            System.out.println("| 1. View Booking                                                   |");
            System.out.println("|                                                                   |");
            System.out.println("| 2. Cancel Booking                                                 |");
            System.out.println("|                                                                   |");
            System.out.println("| 3. Back                                                           |");
            System.out.println("|                                                                   |");
            System.out.println("+-------------------------------------------------------------------+\n");

            System.out.print("Choice your action: ");
            choice = getScanner().nextInt();
            BookingBL bbl = new BookingBL();
            switch (choice) {
                case 1:
                    bookingUI.viewBooking(UserDAL.user_id);
                    // bbl.viewBooking(UserDAL.user_id);
                    break;
                case 2:
                    bbl.cancelBooking(bookingID);
                    break;
                case 3:
                    cusScreen();
                    break;
                default:
                    System.out.println("Function does not exist !");
                    break;
            }
        }
    }

    public static void menu() {
        ClearScreen.clear();
        while (true) {
            Header.header();
            System.out.println("=====================================================================");
            System.out.println("|                                 MENU                              |");
            System.out.println("+-------------------------------------------------------------------+");
            System.out.println("|                                                                   |");
            System.out.println("| 1. Login                                                          |");
            System.out.println("|                                                                   |");
            System.out.println("| 2. Booking Flight                                                 |");
            System.out.println("|                                                                   |");
            System.out.println("| 3. Exit                                                           |");
            System.out.println("|                                                                   |");
            System.out.println("+-------------------------------------------------------------------+\n");

            System.out.print("Choice your action: ");
            choice = getScanner().nextInt();
            switch (choice) {
                case 1:
                    // ClearScreen.clear();
                    UserUI.login(email, pass);
                    break;
                case 2:
                    ClearScreen.clear();
                    bookingUI.booking(quantity, bookingDate, totalCost, UserDAL.user_id, flightID);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }

    public static void adminScreen() {
        System.out.println("This is admin screen");
    }

    public static Scanner getScanner() {
        return new Scanner(System.in);
    }
}
