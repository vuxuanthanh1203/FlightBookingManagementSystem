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
        switch (choice) {
            case "economy":
                if (FlightDAL.checkERemain(flightID) == 0) {
                    System.out.println("\n-- SOLD OUT !!!");
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
                    System.out.println("\n-- SOLD OUT !!!");
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
                    System.out.println("\n-- SOLD OUT !!!");
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

    public static Scanner getScanner() {
        return new Scanner(System.in);
    }
}
