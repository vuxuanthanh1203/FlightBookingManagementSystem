package group3.ui;

import java.util.Scanner;

import group3.dal.UserDAL;
import group3.persistance.ClearScreen;

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
    static String bookingID = "";
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
        menuManageBooking();
        System.out.print("Choice your action: ");
        choice = getScanner().nextInt();
        switch (choice) {
            case 1:
                bookingUI.viewBooking(UserDAL.user_id);
                break;
            case 2:
                bookingUI.cancelBooking(bookingID, UserDAL.user_id);
                break;
            case 3:
                ClearScreen.clear();
                cusScreen();
                break;
            default:
                System.out.println("Function does not exist !");
                break;
        }
    }

    public static void menuManageBooking() {
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
            System.out.println("| 3. Manage Booking                                                 |");
            System.out.println("|                                                                   |");
            System.out.println("| 4. Exit                                                           |");
            System.out.println("|                                                                   |");
            System.out.println("+-------------------------------------------------------------------+\n");

            System.out.print("Choice your action: ");
            choice = getScanner().nextInt();
            switch (choice) {
                case 1:
                    UserUI.login(email, pass);
                    break;
                case 2:
                    ClearScreen.clear();
                    FlightUI.chooseSchedule();
                    bookingUI.booking(quantity, bookingDate, totalCost, UserDAL.user_id, flightID);
                    break;
                case 3:
                    ClearScreen.clear();
                    bookingUI.viewBookingGuest(email);
                    break;
                case 4:
                    ClearScreen.clear();
                    Header.header();
                    System.out.println("\n\t\t   (^.^) Thanks for choosing us ! (^.^)\n");
                    Header.footer();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }

    public static void adminScreen() {
        Header.header();
        System.out.println("   Welcome Admin " + UserDAL.user_name + " To Flight Booking Management System !");
        while (true) {
            System.out.println("\n=====================================================================");
            System.out.println("|                                MENU                               |");
            System.out.println("+-------------------------------------------------------------------+");
            System.out.println("|                                                                   |");
            System.out.println("| 1. Manage Flight                                                  |");
            System.out.println("|                                                                   |");
            System.out.println("| 2. Manage Booking                                                 |");
            System.out.println("|                                                                   |");
            System.out.println("| 3. Logout                                                         |");
            System.out.println("|                                                                   |");
            System.out.println("+-------------------------------------------------------------------+\n");

            System.out.print("Choice your action: ");
            choice = getScanner().nextInt();
            switch (choice) {
                case 1:
                    AdminUI.manageFlight();
                    break;
                case 2:
                    adminManageBooking();
                    break;
                case 3:
                    UserDAL.resetKey();
                    menu();
                    break;
                default:
                    System.out.println("\n-- Function does not exist ! --");
                    break;
            }
        }
    }

    public static void adminManageBooking() {
        menuManageBooking();
        System.out.print("Choice your action: ");
        choice = getScanner().nextInt();
        switch (choice) {
            case 1:
                bookingUI.viewAllBooking();
                break;
            case 2:
                bookingUI.cancelABooking(bookingID);
                break;
            case 3:
                ClearScreen.clear();
                adminScreen();
                break;
            default:
                ClearScreen.clear();
                System.out.println("Function does not exist !");
                break;
        }
    }

    public static Scanner getScanner() {
        return new Scanner(System.in);
    }
}
