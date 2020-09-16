package group3;
import group3.dal.BookingDAL;
import group3.dal.FlightDAL;
import group3.ui.BookingUI;
import group3.ui.FlightUI;
import group3.ui.UserUI;

public class App {
    public static void main(String[] args) {
        // UserDAL.login("vxt@gmail.com", "1234");
        // UserDAL.register("", "");
        FlightUI fui = new FlightUI();
        FlightDAL fdal = new FlightDAL();
        int quantity = 1;
        int adult = 1;
        int children = 1;
        String bookingDate = null;
        double totalCost = 1;
        int userID = 1;
        int flightID = 1;
        UserUI ui = new UserUI();
        // ui.loginScreen(email, pass);
        // ui.registerScreen(email, pass, name, tel, id, address);
        // ui.modifyAccount(email, name, tel, address);
        // ui.changePass(email, oldPass, newPass);
        BookingUI bui = new BookingUI();
        fui.displayFlight(1, "2020/09/19");
        bui.booking(quantity, adult, children, bookingDate, totalCost, userID, flightID);
        // // fdal.updateBRemain(4, 3);
        fui.displayFlight(1, "2020/09/19");
    }
}
