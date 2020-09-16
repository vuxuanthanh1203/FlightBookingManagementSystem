package group3.persistance;

import java.util.Scanner;

import group3.dal.FlightDAL;
import group3.ui.UserUI;

public class Booking {
    private int quantity;
    private String bookingDate;
    private double totalCost;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public static int getFlight(int flightID) {
        flightID = 0;
        while (true) {
            System.out.print("\n- flightID: ");
            flightID = getScanner().nextInt();
            if (flightID == 0) {
                UserUI.fieldBlank("|               -- flightID is not blank --           |");
                continue;
            } else if (!FlightDAL.chooseFlight(flightID)) {
                UserUI.fieldBlank("|               -- Flight does not exist --           |");
                continue;
            }
            break;
        }
        return flightID;
    }

    public static int getAdults(int adult) {
        adult = 0;
        while (true) {
            System.out.print("\n- Adult: ");
            adult = getScanner().nextInt();
            if (adult == 0) {
                UserUI.fieldBlank("|                -- Adult is not blank --             |");
                continue;
            }
            break;
        }
        return adult;
    }

    public static int getChildren(int children) {
        children = 0;
        while (true) {
            System.out.print("\n- Children (> 12years old): ");
            children = getScanner().nextInt();
            break;
        }
        return children;
    }

    public static Scanner getScanner() {
        return new Scanner(System.in);
    }
}
