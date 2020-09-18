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

    private static Connection getConnection() throws SQLException {
        Connection conn = DbUtil.getInstance().getConnection();
        return conn;
    }

    public void booking(int quantity, String bookingDate, double totalCost, int userID, int flightID) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String sql = "INSERT INTO bookings(quantity, booking_date, total_cost, flight_id, user_id) VALUES (?, ?, ?, ?, ?)";
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, quantity);
            pstmt.setString(2, dateFormat.format(date));
            pstmt.setDouble(3, totalCost);
            pstmt.setInt(4, flightID);
            pstmt.setInt(5, userID);
            int k = pstmt.executeUpdate();
            if (k == 1) {
                System.out.println("\n-- Booking Successful ! --\n");
            } else {
                System.out.println("\n-- Booking Failed !!! --\n");
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
                // System.out.println(result);
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
                // System.out.println(result);
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
                // System.out.println(result);
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
                System.out.print("\n- Booking ID: " + rs.getInt("booking_id"));
                System.out.println("\t\t\t\t\t\t\t\t- Flight Number: " + rs.getString("flight_num"));
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

    public void viewAllBooking() {
        try {
            String sql = "CALL view_all_booking()";
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.print("\n- Booking ID: " + rs.getInt("booking_id"));
                System.out.println("\t\t\t\t\t\t\t\t- Flight Number: " + rs.getString("flight_num"));
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

    public void selectBooking(int bookingID, int userID) {
        int booking = 0;
        try {
            String sql = "CALL select_booking('" + userID + "','" + bookingID + "')";
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                booking = rs.getInt("booking_id");

                System.out.print("\n- Booking ID: " + rs.getInt("booking_id"));
                System.out.println("\t\t\t\t\t\t\t\t- Flight Number: " + rs.getString("flight_num"));
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
            if (bookingID != booking) {
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

    public void selectABooking(int bookingID) {
        int booking = 0;
        try {
            String sql = "CALL select_a_booking('" + bookingID + "')";
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                booking = rs.getInt("booking_id");

                System.out.print("\n- Booking ID: " + rs.getInt("booking_id"));
                System.out.println("\t\t\t\t\t\t\t\t- Flight Number: " + rs.getString("flight_num"));
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
            if (bookingID != booking) {
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

    public void cancelBooking(int bookingID) {
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
                Header.header();
                System.out.println("\n-- Enter To Be Back ! --");
                getScanner().nextLine();
                MenuUI.adminManageBooking();
                System.out.println("-- Delete Failed !!! --");
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
