package group3.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import group3.persistance.ClearScreen;
import group3.persistance.RandomID;
import group3.ui.BookingUI;
import group3.ui.Header;
import group3.ui.MenuUI;

public class BookingDAL {
    private static Connection connection = null;
    private static PreparedStatement pstmt = null;
    private static ResultSet rs = null;

    public static String departure = "";
    public static String arrival = "";
    public static double total = 0;
    public static String result = "";
    public static int count = 0;

    private static Connection getConnection() throws SQLException {
        Connection conn = DbUtil.getInstance().getConnection();
        return conn;
    }

    public void booking(int quantity, String bookingDate, double totalCost, int userID, int flightID) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String sql = "INSERT INTO bookings(booking_id, quantity, booking_date, total_cost, flight_id, user_id) VALUES (?, ?, ?, ?, ?, ?)";
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, "BK-" + RandomID.random());
            pstmt.setInt(2, quantity);
            pstmt.setString(3, dateFormat.format(date));
            pstmt.setDouble(4, totalCost);
            pstmt.setInt(5, flightID);
            pstmt.setInt(6, userID);
            int k = pstmt.executeUpdate();
            if (k == 1) {
                ClearScreen.clear();
                System.out.println("\n-- Booking Successful ! --\n");
            } else {
                ClearScreen.clear();
                System.out.println("\n-- Booking Failed !!! --\n");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public void bookingGuest(int quantity, String bookingDate, double totalCost, int userID, int flightID) {
        try {
            int last_id = UserDAL.selectLastUser();
            System.out.println("last id = " + last_id);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String sql = "INSERT INTO bookings(booking_id, quantity, booking_date, total_cost, flight_id, user_id) VALUES (?, ?, ?, ?, ?, ?)";
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1,"BK-"+ RandomID.random());
            pstmt.setInt(2, quantity);
            pstmt.setString(3, dateFormat.format(date));
            pstmt.setDouble(4, totalCost);
            pstmt.setInt(5, flightID);
            pstmt.setInt(6, last_id);
            int k = pstmt.executeUpdate();
            if (k == 1) {
                ClearScreen.clear();
                Header.header();
                System.out.println("\n-- Booking Successful ! --\n");
                System.out.println("\n-- Enter to Continue ! --\n");
                getScanner().nextLine();
                MenuUI.menu();
            } else {
                ClearScreen.clear();
                System.out.println("\n-- Booking Failed !!! --\n");
                System.out.println("\n-- Enter to Continue ! --\n");
                getScanner().nextLine();
                MenuUI.menu();
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public static String setEPrice(int quantity) {

        try {
            String sql = "SELECT * FROM fares";
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                total = rs.getDouble("e_fare") * quantity;
                result = total + "00.000 VND";
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return result;
    }

    public static String setPPrice(int quantity) {
        try {
            String sql = "SELECT * FROM fares";
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                total = rs.getDouble("p_fare") * quantity;
                result = total + "00.000 VND";
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return result;
    }

    public static String setBPrice(int quantity) {
        double total = 0;
        try {
            String sql = "SELECT * FROM fares";
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                total = rs.getDouble("b_fare") * quantity;
                result = total + "00.000 VND";
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return result;
    }

    public void viewBooking(int userID) {
        try {
            String sql = "CALL view_booking('" + userID + "')";
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.print("\n- Booking ID: " + rs.getString("booking_id"));
                System.out.println("\t\t\t\t\t\t\t- Flight Number: " + rs.getString("flight_num"));
                System.out.print("\n- Flight Time: " + rs.getString("flight_time"));
                System.out.println("\t\t\t\t\t\t\t- Full Name: " + rs.getString("full_name"));
                System.out.print("\n- Departure Time: " + rs.getString("departure_time"));
                System.out.print("  - Arrival Time: " + rs.getString("arrival_time"));
                System.out.println("\t\t\t- Address: " + rs.getString("address"));
                System.out.print("\n- Flight Date: " + rs.getString("flight_date"));
                System.out.println("\t\t\t\t\t\t- Quantity: " + rs.getInt("quantity"));
                System.out.print("\n- Departure Location: " + rs.getString("departure_loc"));
                System.out.print("  - Arrival Location: " + rs.getString("arrival_loc"));
                System.out.println("\t\t\t- Status: " + rs.getString("b_status") + "\n");
                line();
            } else {
                System.out.println("\n-- No matching results --\n");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public int viewBookingGuest(String email) {
        try {
            String sql = "CALL view_booking_guest('" + BookingUI.guest_email + "')";
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) { 
                count ++;
                System.out.print("\n- Booking ID: " + rs.getString("booking_id"));
                System.out.println("\t\t\t\t\t\t\t- Flight Number: " + rs.getString("flight_num"));
                System.out.print("\n- Flight Time: " + rs.getString("flight_time"));
                System.out.println("\t\t\t\t\t\t\t- Full Name: " + rs.getString("full_name"));
                System.out.print("\n- Departure Time: " + rs.getString("departure_time"));
                System.out.print("  - Arrival Time: " + rs.getString("arrival_time"));
                System.out.println("\t\t\t- Address: " + rs.getString("address"));
                System.out.print("\n- Flight Date: " + rs.getString("flight_date"));
                System.out.println("\t\t\t\t\t\t- Quantity: " + rs.getInt("quantity"));
                System.out.print("\n- Departure Location: " + rs.getString("departure_loc"));
                System.out.print("  - Arrival Location: " + rs.getString("arrival_loc"));
                System.out.println("\t\t\t- Status: " + rs.getString("b_status") + "\n");
                line();
            } else {
                System.out.println("\n-- No matching results --\n");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return count;
    }

    public void viewAllBooking() {
        try {
            String sql = "CALL view_all_booking()";
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.print("\n- Booking ID: " + rs.getString("booking_id"));
                System.out.println("\t\t\t\t\t\t\t- Flight Number: " + rs.getString("flight_num"));
                System.out.print("\n- Flight Time: " + rs.getString("flight_time"));
                System.out.println("\t\t\t\t\t\t\t- Full Name: " + rs.getString("full_name"));
                System.out.print("\n- Departure Time: " + rs.getString("departure_time"));
                System.out.print("  - Arrival Time: " + rs.getString("arrival_time"));
                System.out.println("\t\t\t- Address: " + rs.getString("address"));
                System.out.print("\n- Flight Date: " + rs.getString("flight_date"));
                System.out.println("\t\t\t\t\t\t- Quantity: " + rs.getInt("quantity"));
                System.out.print("\n- Departure Location: " + rs.getString("departure_loc"));
                System.out.print("  - Arrival Location: " + rs.getString("arrival_loc"));
                System.out.println("\t\t\t- Status: " + rs.getString("b_status") + "\n");
                line();
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public void selectBooking(String bookingID, int userID) {
        String booking = "";
        try {
            String sql = "CALL select_booking('" + userID + "','" + bookingID + "')";
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                booking = rs.getString("booking_id");

                System.out.print("\n- Booking ID: " + rs.getString("booking_id"));
                System.out.println("\t\t\t\t\t\t\t- Flight Number: " + rs.getString("flight_num"));
                System.out.print("\n- Flight Time: " + rs.getString("flight_time"));
                System.out.println("\t\t\t\t\t\t\t- Full Name: " + rs.getString("full_name"));
                System.out.print("\n- Departure Time: " + rs.getString("departure_time"));
                System.out.print("  - Arrival Time: " + rs.getString("arrival_time"));
                System.out.println("\t\t\t- Address: " + rs.getString("address"));
                System.out.print("\n- Flight Date: " + rs.getString("flight_date"));
                System.out.println("\t\t\t\t\t\t- Quantity: " + rs.getInt("quantity"));
                System.out.print("\n- Departure Location: " + rs.getString("departure_loc"));
                System.out.print("  - Arrival Location: " + rs.getString("arrival_loc"));
                System.out.println("\t\t\t- Status: " + rs.getString("b_status") + "\n");
                line();
            }
            if (!bookingID.equals(booking)) {
                ClearScreen.clear();
                Header.header();
                System.out.println("\n-- Booking Does Not Exist ! --\n");
                System.out.print("-- Enter to be back !");
                getScanner().nextLine();
                MenuUI.manageBooking();
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public void selectABooking(String bookingID) {
        String booking = "";
        try {
            String sql = "CALL select_a_booking('" + bookingID + "')";
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                booking = rs.getString("booking_id");

                System.out.print("\n- Booking ID: " + rs.getString("booking_id"));
                System.out.println("\t\t\t\t\t\t\t- Flight Number: " + rs.getString("flight_num"));
                System.out.print("\n- Flight Time: " + rs.getString("flight_time"));
                System.out.println("\t\t\t\t\t\t\t- Full Name: " + rs.getString("full_name"));
                System.out.print("\n- Departure Time: " + rs.getString("departure_time"));
                System.out.print("  - Arrival Time: " + rs.getString("arrival_time"));
                System.out.println("\t\t\t- Address: " + rs.getString("address"));
                System.out.print("\n- Flight Date: " + rs.getString("flight_date"));
                System.out.println("\t\t\t\t\t\t- Quantity: " + rs.getInt("quantity"));
                System.out.print("\n- Departure Location: " + rs.getString("departure_loc"));
                System.out.print("  - Arrival Location: " + rs.getString("arrival_loc"));
                System.out.println("\t\t\t- Status: " + rs.getString("b_status") + "\n");
                line();
            }
            if (!bookingID.equals(booking)) {
                ClearScreen.clear();
                Header.header();
                System.out.println("\n-- Booking Does Not Exist ! --\n");
                System.out.print("-- Enter to be back !");
                getScanner().nextLine();
                MenuUI.manageBooking();
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public void cancelBooking(String bookingID) {
        try {
            String sql = "DELETE FROM bookings WHERE booking_id = '" + bookingID + "'";
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            int k = pstmt.executeUpdate();
            if (k == 1) {
                Header.header();
                System.out.println("\n-- Delete Complete ! --");
                System.out.println("\n-- Enter To Be Back ! --");
                getScanner().nextLine();
                MenuUI.adminManageBooking();
            } else {
                ClearScreen.clear();
                Header.header();
                System.out.println("-- Delete Failed !!! --");
                System.out.println("\n-- Enter To Be Back ! --");
                getScanner().nextLine();
                MenuUI.adminManageBooking();
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public void cancelBookingGuest(String bookingID) {
        try {
            String sql = "DELETE FROM bookings WHERE booking_id = '" + bookingID + "'";
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            int k = pstmt.executeUpdate();
            if (k == 1) {
                ClearScreen.clear();
                Header.header();
                System.out.println("\n-- Delete Complete ! --");
                System.out.println("\n-- Enter To Be Back ! --");
                getScanner().nextLine();
            } else {
                ClearScreen.clear();
                Header.header();
                System.out.println("-- Delete Failed !!! --");
                System.out.println("\n-- Enter To Be Back ! --");
                getScanner().nextLine();
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public static void selectRoute(int route) {
        try {
            String sql = "SELECT * FROM routes WHERE route_id = '" + route + "'";
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                departure = rs.getString("departure_loc");
                arrival = rs.getString("arrival_loc");
            } else {
                ClearScreen.clear();
                System.out.println("\n- Cannot get role !!!");
                MenuUI.cusScreen();
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public void line() {
        System.out.println(
                "-----------------------------------------------------------------------------------------------------------------------");
    }

    public static Scanner getScanner() {
        return new Scanner(System.in);
    }
}
