package group3.ui;

import java.util.Scanner;

import group3.bl.BookingBL;
import group3.dal.BookingDAL;
import group3.dal.FlightDAL;
import group3.persistance.Booking;
import group3.persistance.ClearScreen;

public class BookingUI {
    private BookingBL bal = new BookingBL();

    public void booking(int quantity, int adult, int children, String bookingDate, double totalCost, int userID,
            int flightID) {
        // ClearScreen.clear();
        quantity = 0;
        double total = 0;
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
                    System.out.println("\n-- SOLD OUT !!! --");
                } else {
                    System.out.printf("\n- Grand Total: ");
                    BookingDAL.setEPrice(quantity);
                    System.out.println("");
                    System.out.print("\n- Confirm Booking (Y/N): ");
                    yon = getScanner().nextLine().toLowerCase();
                    switch (yon) {
                        case "y":
                            FlightDAL.updateERemain(quantity, flightID);
                            bal.booking(quantity, bookingDate, totalCost, userID, flightID);
                            break;
                        case "n":
                            System.out.println("previous menu");
                            break;
                        default:
                            System.out.println("Function does not exist !");
                            break;
                    }
                }
                break;
            case "premium":
                if (FlightDAL.checkPRemain(flightID) == 0) {
                    System.out.println("\n-- SOLD OUT !!! --");
                } else {
                    System.out.printf("\n- Grand Total: ");
                    BookingDAL.setPPrice(quantity);
                    System.out.println("");
                    System.out.print("\n- Confirm Booking (Y/N): ");
                    yon = getScanner().nextLine().toLowerCase();
                    switch (yon) {
                        case "y":
                            FlightDAL.updatePRemain(quantity, flightID);
                            bal.booking(quantity, bookingDate, totalCost, userID, flightID);
                            break;
                        case "n":
                            System.out.println("previous menu");
                            break;
                        default:
                            System.out.println("Function does not exist !");
                            break;
                    }
                }
                break;
            case "business":
                if (FlightDAL.checkBRemain(flightID) == 0) {
                    System.out.println("\n-- SOLD OUT !!! --");
                } else {
                    System.out.printf("\n- Grand Total: ");
                    BookingDAL.setBPrice(quantity);
                    System.out.println("");
                    System.out.print("\n- Confirm Booking (Y/N): ");
                    yon = getScanner().nextLine().toLowerCase();
                    switch (yon) {
                        case "y":
                            FlightDAL.updateBRemain(quantity, flightID);
                            bal.booking(quantity, bookingDate, totalCost, userID, flightID);
                            break;
                        case "n":
                            System.out.println("previous menu");
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

    public void viewBooking(int userID) {
        System.out.println(
                "\n======================================================================================================================");
        System.out.println(
                "|                                                  LIST OF BOOKING                                                   |");
        System.out.println(
                "======================================================================================================================");
        bal.viewBooking(userID);
        System.out.println(
                "-----------------------------------------------------------------------------------------------------------------------");
    }

    public void selectBooking(int bookingID, int userID) {
        System.out.println(
                "\n======================================================================================================================");
        System.out.println(
                "|                                                   BOOKING DETAIL                                                   |");
        System.out.println(
                "======================================================================================================================");
        bal.selectBooking(bookingID, userID);
        System.out.println(
                "-----------------------------------------------------------------------------------------------------------------------");
    }

    public void cancelBooking(int bookingID, int userID) {
        System.out.println(
                "\n======================================================================================================================");
        System.out.println(
                "|                                                   CANCEL BOOKING                                                   |");
        System.out.println(
                "======================================================================================================================");
        System.out.print("- Enter Booking ID: ");
        bookingID = getScanner().nextInt();
        selectBooking(bookingID, userID);
        System.out.println("\n- Do You Really Want To Delete The Ticket ? (Y/N)");
        String choice = getScanner().nextLine().toLowerCase();
        switch (choice) {
            case "y":
                bal.cancelBooking(bookingID);
                break;
            case "n":
                System.out.println("\n- No Cancel !");
                viewBooking(userID);
                break;
            default:
                System.out.println("\n-- Function does not exist ! --\n");
                break;
        }
    }

    public static Scanner getScanner() {
        return new Scanner(System.in);
    }
}
