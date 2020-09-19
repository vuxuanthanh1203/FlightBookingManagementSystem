package group3.ui;

import java.util.Scanner;

import group3.bl.BookingBL;
import group3.dal.BookingDAL;
import group3.dal.FlightDAL;
import group3.dal.UserDAL;
import group3.persistance.Booking;
import group3.persistance.ClearScreen;
import group3.persistance.User;

public class BookingUI {
    private static int adult;
    private static int children;
    private static String name;
    private static String tel;
    private static String id;
    private static String address;
    public static String guest_email;
    int route = 0;
    String flightDate = "";
    private String email;
    // private String pass;
    private static String type = "";
    private static String total;

    private BookingBL bbl = new BookingBL();

    public void booking(int quantity, String bookingDate, double totalCost, int userID, int flightID) {

        System.out.println("\n\n=====================================================================");
        System.out.println("|                         SELECT TRAVEL OPTION                      |");
        System.out.println("+-------------------------------------------------------------------+");
        flightID = Booking.getFlight(flightID);
        adult = Booking.getAdults(adult);
        children = Booking.getChildren(children);
        quantity = adult + children;
        System.out.print("\n- Choose a seat type: ");
        type = getScanner().nextLine().toLowerCase();
        String yon;
        switch (type) {
            case "economy":
                if (FlightDAL.checkERemain(flightID) == 0) {
                    ClearScreen.clear();
                    Header.header();
                    System.out.println("\n-- SOLD OUT !!! --");
                    System.out.println("\n- Enter To Be Back ! -");
                    getScanner().nextLine();
                    ClearScreen.clear();
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
                            if (!UserDAL.isLogin) {
                                UserUI.registerPreOder(email, name, tel, id, address);
                                bookingSummary();
                                System.out.print("\n- Confirm Booking (Y/N): ");
                                String conf = getScanner().nextLine().toLowerCase();
                                switch (conf) {
                                    case "y":
                                        FlightDAL.updateERemain(quantity, flightID);
                                        bbl.bookingGuest(quantity, bookingDate, totalCost, userID, flightID);
                                        break;
                                    case "n":
                                        ClearScreen.clear();
                                        Header.header();
                                        System.out.println("\n- No Reservation !");
                                        System.out.println("\n-- Enter to Continue ! --\n");
                                        getScanner().nextLine();
                                        MenuUI.menu();
                                        break;
                                    default:
                                        break;
                                }
                            } else {
                                FlightDAL.updateERemain(quantity, flightID);
                                bbl.booking(quantity, bookingDate, totalCost, userID, flightID);
                                MenuUI.cusScreen();
                            }
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
                    Header.header();
                    System.out.println("\n-- SOLD OUT !!! --");
                    System.out.println("\n- Enter To Be Back ! -");
                    getScanner().nextLine();
                    ClearScreen.clear();
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
                            if (!UserDAL.isLogin) {
                                UserUI.registerPreOder(email, name, tel, id, address);
                                bookingSummary();
                                System.out.print("\n- Confirm Booking (Y/N): ");
                                String conf = getScanner().nextLine().toLowerCase();
                                switch (conf) {
                                    case "y":
                                        FlightDAL.updatePRemain(quantity, flightID);
                                        bbl.bookingGuest(quantity, bookingDate, totalCost, userID, flightID);
                                        break;
                                    case "n":
                                        ClearScreen.clear();
                                        Header.header();
                                        System.out.println("\n- No Reservation !");
                                        System.out.println("\n-- Enter to Continue ! --\n");
                                        getScanner().nextLine();
                                        MenuUI.menu();
                                        break;
                                    default:
                                        break;
                                }
                            } else {
                                FlightDAL.updatePRemain(quantity, flightID);
                                bbl.booking(quantity, bookingDate, totalCost, userID, flightID);
                                MenuUI.cusScreen();
                            }
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
                    Header.header();
                    System.out.println("\n-- SOLD OUT !!! --");
                    System.out.println("\n- Enter To Be Back ! -");
                    getScanner().nextLine();
                    ClearScreen.clear();
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
                            if (!UserDAL.isLogin) {
                                UserUI.registerPreOder(email, name, tel, id, address);
                                bookingSummary();
                                System.out.print("\n- Confirm Booking (Y/N): ");
                                String conf = getScanner().nextLine().toLowerCase();
                                switch (conf) {
                                    case "y":
                                        FlightDAL.updateBRemain(quantity, flightID);
                                        bbl.bookingGuest(quantity, bookingDate, totalCost, userID, flightID);
                                        break;
                                    case "n":
                                        ClearScreen.clear();
                                        Header.header();
                                        System.out.println("\n- No Reservation !");
                                        System.out.println("\n-- Enter to Continue ! --\n");
                                        getScanner().nextLine();
                                        MenuUI.menu();
                                        break;
                                    default:
                                        break;
                                }
                            } else {
                                FlightDAL.updateBRemain(quantity, flightID);
                                bbl.booking(quantity, bookingDate, totalCost, userID, flightID);
                                MenuUI.cusScreen();
                            }
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
                ClearScreen.clear();
                System.out.println("\n - Invalid Selection !");
                break;
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
        MenuUI.manageBooking();
    }

    public static void searchBookingGuest(String email) {
        ClearScreen.clear();
        Header.header();

        System.out.println("\n=====================================================================");
        System.out.println("|                             SEARCH BOOKING                        |");
        System.out.println("+-------------------------------------------------------------------+");
        System.out.println("\n(0: Back)");
        guest_email = User.getEmail(email);
    }

    public void viewBookingGuest(String email) {
        searchBookingGuest(email);
        ClearScreen.clear();
        Header.header();
        System.out.println(
                "\n======================================================================================================================");
        System.out.println(
                "|                                                  BOOKING DETAILS                                                   |");
        System.out.println(
                "======================================================================================================================");
        bbl.viewBookingGuest(email);
        System.out.println(
                "-----------------------------------------------------------------------------------------------------------------------");
        System.out.println("\n 1 - Cancel / 2 - Back ");
        System.out.print("\n- Input your choose: ");
        int choose = getScanner().nextInt();

        switch (choose) {
            case 1:
                if (BookingDAL.count == 0) {
                    ClearScreen.clear();
                    Header.header();
                    System.out.println("\n- You Don't Have Booking !!!");
                    System.out.println("\n- Enter To Continue ! -");
                    getScanner().nextLine();
                    MenuUI.menu();
                } else {
                    System.out.print("\n- Input The Booking ID: ");
                    String booking_ID = getScanner().nextLine();
                    bbl.cancelBookingGuest(booking_ID);
                    MenuUI.menu();
                }
                break;
            case 2:
                MenuUI.menu();
            default:
                break;
        }
        ClearScreen.clear();
        MenuUI.manageBooking();
    }

    public void viewAllBooking() {
        ClearScreen.clear();
        Header.header();
        System.out.println(
                "\n======================================================================================================================");
        System.out.println(
                "|                                                  LIST OF BOOKING                                                   |");
        System.out.println(
                "======================================================================================================================");
        bbl.viewAllBooking();
        System.out.println(
                "-----------------------------------------------------------------------------------------------------------------------");
        System.out.print("\n- Enter to be back !");
        getScanner().nextLine();
        ClearScreen.clear();
        MenuUI.adminManageBooking();
    }

    public void selectBooking(String bookingID, int userID) {
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

    public void selectABooking(String bookingID) {
        ClearScreen.clear();
        Header.header();
        System.out.println(
                "\n======================================================================================================================");
        System.out.println(
                "|                                                   BOOKING DETAIL                                                   |");
        System.out.println(
                "======================================================================================================================");
        bbl.selectABooking(bookingID);
        System.out.println(
                "-----------------------------------------------------------------------------------------------------------------------");
    }

    public void cancelBooking(String bookingID, int userID) {
        ClearScreen.clear();
        Header.header();
        System.out.println(
                "\n======================================================================================================================");
        System.out.println(
                "|                                                   CANCEL BOOKING                                                   |");
        System.out.println(
                "======================================================================================================================");
        System.out.println("\n(back: Back)");
        System.out.print("\n- Enter Booking ID: ");
        bookingID = getScanner().nextLine();
        if (bookingID.equalsIgnoreCase("back")) {
            ClearScreen.clear();
            MenuUI.manageBooking();
        } else {
            selectBooking(bookingID, UserDAL.user_id);
            System.out.println("\n- Do You Really Want To Delete The Reservation ? (Y/N)");
            String choice = getScanner().nextLine().toLowerCase();
            switch (choice) {
                case "y":
                    ClearScreen.clear();
                    bbl.cancelBooking(bookingID);
                    break;
                case "n":
                    ClearScreen.clear();
                    Header.header();
                    System.out.println("\n- No Cancel !");
                    System.out.print("\n- Enter To Be Back !");
                    getScanner().nextLine();
                    MenuUI.manageBooking();
                    break;
                default:
                    ClearScreen.clear();
                    System.out.println("\n-- Function does not exist ! --\n");
                    MenuUI.manageBooking();
                    break;
            }
        }

    }

    public void cancelABooking(String bookingID) {
        ClearScreen.clear();
        Header.header();
        System.out.println(
                "\n======================================================================================================================");
        System.out.println(
                "|                                                   CANCEL BOOKING                                                   |");
        System.out.println(
                "======================================================================================================================");
        System.out.print("\n- Enter Booking ID: ");
        bookingID = getScanner().nextLine();
        selectABooking(bookingID);
        System.out.println("\n- Do You Really Want To Delete The Reservation ? (Y/N)");
        String choice = getScanner().nextLine().toLowerCase();
        switch (choice) {
            case "y":
                ClearScreen.clear();
                bbl.cancelBooking(bookingID);
                break;
            case "n":
                ClearScreen.clear();
                Header.header();
                System.out.println("\n- No Cancel !");
                System.out.print("\n- Enter To Be Back !");
                getScanner().nextLine();
                MenuUI.adminManageBooking();
                break;
            default:
                ClearScreen.clear();
                System.out.println("\n-- Function does not exist ! --\n");
                MenuUI.adminManageBooking();
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
        System.out.println("\n  - Seat Type: " + type);
        System.out.println("\n  - From:  " + BookingDAL.departure + "\t\t\t To:  " + BookingDAL.arrival);
        System.out.println("\n  - Departure Date: " + FlightUI.date);
        System.out.println("\n  - Grand Total: " + total);
        System.out.println("\n    Number of Passengers:");
        System.out.println("\n  - Adults:  " + adult + "\t\t\t Children:  " + children);
        System.out.println("=====================================================================");
    }

    public static Scanner getScanner() {
        return new Scanner(System.in);
    }
}
