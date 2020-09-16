package group3.bl;

import group3.dal.BookingDAL;

public class BookingBL {
    private BookingDAL bdal = new BookingDAL();

    public void booking(int quantity, String bookingDate, double totalCost, int userID, int flightID) {
        bdal.booking(quantity, bookingDate, totalCost, userID, flightID);
    }
}
