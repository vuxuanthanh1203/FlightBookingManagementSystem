package group3.ui;

import java.util.Scanner;

import group3.bl.BookingBL;
import group3.bl.FlightBL;
import group3.dal.BookingDAL;
import group3.dal.FlightDAL;
import group3.dal.UserDAL;
import group3.persistance.Booking;
import group3.persistance.ClearScreen;

public class BookingUI {
    static int adult;
    static int children;
    int route = 0;
    String flightDate = "";
    private String email;
    private String pass;
    private String name;
    private String tel;
    private String id;
    private String address;
    private static String total;

    private BookingBL bbl = new BookingBL();
    private FlightBL fBl = new FlightBL();

    public void booking(int quantity, String bookingDate, double totalCost, int userID, int flightID) {
        // ClearScreen.clear();
        // quantity = 0;
        if (!UserDAL.isLogin) {
            UserUI.registerScreen(email, pass, name, tel, id, address);
        } else {
            // fBl.displayFlight();
            System.out.println("\n\n=====================================================================");
            System.out.println("|                         SELECT TRAVEL OPTION                      |");
            System.out.println("+-------------------------------------------------------------------+");
            flightID = Booking.getFlight(flightID);
            adult = Booking.getAdults(adult);
            children = Booking.getChildren(children);
            quantity = adult + children;
            System.out.print("\n- Choose a seat type: ");
            String choice = getScanner().nextLine();
            String yon;
            // BookingDAL bDal = new BookingDAL();
            switch (choice) {
                case "economy":
                    if (FlightDAL.checkERemain(flightID) == 0) {
                        ClearScreen.clear();
                        System.out.println("\n-- SOLD OUT !!! --");
                        if (UserDAL.isLogin) {
                            MenuUI.cusScreen();
                        } else {
                            MenuUI.menu();
                        }
                    } else {
                        total = BookingDAL.setEPrice(quantity);
                        bookingSummary();
                        System.out.println("");
                        System.out.print("\n- Confirm Booking (Y/N): ");
                        yon = getScanner().nextLine().toLowerCase();
                        switch (yon) {
                            case "y":
                                ClearScreen.clear();
                                FlightDAL.updateERemain(quantity, flightID);
                                bbl.booking(quantity, bookingDate, totalCost, userID, flightID);
                                MenuUI.cusScreen();
                                break;
                            case "n":
                                ClearScreen.clear();
                                System.out.println("\n- Booking failed !!!");
                                if (UserDAL.isLogin) {
                                    MenuUI.cusScreen();
                                } else {
                                    MenuUI.menu();
                                }
                                break;
                            default:
                                System.out.println("\n- Function does not exist !");
                                break;
                        }
                    }
                    break;
                case "premium":
                    if (FlightDAL.checkPRemain(flightID) == 0) {
                        ClearScreen.clear();
                        System.out.println("\n-- SOLD OUT !!! --");
                        if (UserDAL.isLogin) {
                            MenuUI.cusScreen();
                        } else {
                            MenuUI.menu();
                        }
                    } else {
                        total = BookingDAL.setPPrice(quantity);
                        bookingSummary();
                        System.out.println("");
                        System.out.print("\n- Confirm Booking (Y/N): ");
                        yon = getScanner().nextLine().toLowerCase();
                        switch (yon) {
                            case "y":
                                ClearScreen.clear();
                                FlightDAL.updatePRemain(quantity, flightID);
                                bbl.booking(quantity, bookingDate, totalCost, userID, flightID);
                                MenuUI.cusScreen();
                                break;
                            case "n":
                                ClearScreen.clear();
                                System.out.println("\n- Booking failed !!!");
                                if (UserDAL.isLogin) {
                                    MenuUI.cusScreen();
                                } else {
                                    MenuUI.menu();
                                }
                                break;
                            default:
                                System.out.println("Function does not exist !");
                                break;
                        }
                    }
                    break;
                case "business":
                    if (FlightDAL.checkBRemain(flightID) == 0) {
                        ClearScreen.clear();
                        System.out.println("\n-- SOLD OUT !!! --");
                        if (UserDAL.isLogin) {
                            MenuUI.cusScreen();
                        } else {
                            MenuUI.menu();
                        }
                    } else {
                        total = BookingDAL.setBPrice(quantity);
                        bookingSummary();
                        System.out.println("");
                        System.out.print("\n- Confirm Booking (Y/N): ");
                        yon = getScanner().nextLine().toLowerCase();
                        switch (yon) {
                            case "y":
                                ClearScreen.clear();
                                FlightDAL.updateBRemain(quantity, flightID);
                                bbl.booking(quantity, bookingDate, totalCost, userID, flightID);
                                MenuUI.cusScreen();
                                break;
                            case "n":
                                ClearScreen.clear();
                                System.out.println("\n- Booking failed !!!");
                                if (UserDAL.isLogin) {
                                    MenuUI.cusScreen();
                                } else {
                                    MenuUI.menu();
                                }
                                break;
                            default:
                                System.out.println("Function does not exist !");
                                break;
                        }
                    }
                    break;
                default:
                    UserUI.fieldBlank("|                  -- Invalid Selection --            |");
                    break;
            }
        }
    }

    public void viewBooking(int userID) {
        ClearScreen.clear();
        Header.header();
        System.out.println(
                "\n======================================================================================================================");
        System.out.println(
                "|                                                  LIST OF BOOKING                                                   |");
        System.out.println(
                "======================================================================================================================");
        bbl.viewBooking(userID);
        System.out.println(
                "-----------------------------------------------------------------------------------------------------------------------");
        System.out.print("\n- Enter to be back !");
        getScanner().nextLine();
        ClearScreen.clear();
        MenuUI.cusScreen();
    }

    public void selectBooking(int bookingID, int userID) {
        ClearScreen.clear();
        Header.header();
        System.out.println(
                "\n======================================================================================================================");
        System.out.println(
                "|                                                   BOOKING DETAIL                                                   |");
        System.out.println(
                "======================================================================================================================");
        bbl.selectBooking(bookingID, userID);
        System.out.println(
                "-----------------------------------------------------------------------------------------------------------------------");
    }

    public void cancelBooking(int bookingID, int userID) {
        ClearScreen.clear();
        Header.header();
        System.out.println(
                "\n======================================================================================================================");
        System.out.println(
                "|                                                   CANCEL BOOKING                                                   |");
        System.out.println(
                "======================================================================================================================");
        System.out.print("\n- Enter Booking ID: ");
        bookingID = getScanner().nextInt();
        selectBooking(bookingID, UserDAL.user_id);
        System.out.println("\n- Do You Really Want To Delete The Ticket ? (Y/N)");
        String choice = getScanner().nextLine().toLowerCase();
        switch (choice) {
            case "y":
                ClearScreen.clear();
                bbl.cancelBooking(bookingID);
                break;
            case "n":
                ClearScreen.clear();
                System.out.println("\n- No Cancel !");
                MenuUI.cusScreen();
                break;
            default:
                System.out.println("\n-- Function does not exist ! --\n");
                break;
        }
    }

    public static void bookingSummary() {
        ClearScreen.clear();
        Header.header();
        System.out.println("\n+-------------------------------------------------------------------+");
        System.out.println("|                        -- Booking Summary --                      |");
        System.out.println("+-------------------------------------------------------------------+");
        System.out.println("\n    All prices are in Vietnam Dong");
        System.out.println("\n    One Way Ticket");
        System.out.println("\n  - From:  " + BookingDAL.departure + "\t\t\t To:  " + BookingDAL.arrival);
        System.out.println("\n  - Departure Date: " + FlightUI.date);
        System.out.println("\n  - Grand Total: " + total);
        System.out.println("\n    Number of Passengers:");
        System.out.println("\n  - Adults:  " + adult + "\t\t\t Children:  " + children);
        // System.out.println("+-------------------------------------------------------------------+");
        System.out.println("=====================================================================");
    }

    public static Scanner getScanner() {
        return new Scanner(System.in);
    }
}
