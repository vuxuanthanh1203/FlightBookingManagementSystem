package group3.bl;

import group3.dal.BookingDAL;

public class BookingBL {
    private BookingDAL bDal = new BookingDAL();

    public void booking(int quantity, String bookingDate, double totalCost, int userID, int flightID) {
        bDal.booking(quantity, bookingDate, totalCost, userID, flightID);
    }

    public void viewBooking(int userID) {
        bDal.viewBooking(userID);
    }

    public void viewBookingGuest(String email) {
        bDal.viewBookingGuest(email);
    }

    public void viewAllBooking() {
        bDal.viewAllBooking();
    }

    public void selectBooking(String bookingID, int userID) {
        bDal.selectBooking(bookingID, userID);
    }

    public void selectABooking(String bookingID) {
        bDal.selectABooking(bookingID);
    }

    public void cancelBooking(String bookingID) {
        bDal.cancelBooking(bookingID);
    }

    public void cancelBookingGuest(String bookingID) {
        bDal.cancelBookingGuest(bookingID);
    }

    public void bookingGuest(int quantity, String bookingDate, double totalCost, int userID, int flightID) {
        bDal.bookingGuest(quantity, bookingDate, totalCost, userID, flightID);
    }
}
