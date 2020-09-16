package group3.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BookingDAL {
    private static Connection connection = null;
    private static PreparedStatement pstmt = null;
    private static ResultSet rs = null;

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
            pstmt.setInt(4, flightID );
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

    public static void setEPrice(int quantity) {
        double total = 0;
        try {
            String sql = "SELECT * FROM fares";
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                total = Double.parseDouble(rs.getString("e_fare")) * quantity;
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        System.out.println(total + "00.000 VND");
    }

    public static void setPPrice(int quantity) {
        double total = 0;
        try {
            String sql = "SELECT * FROM fares";
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                total = Double.parseDouble(rs.getString("p_fare")) * quantity;
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        System.out.println(total + "00.000 VND");
    }

    public static void setBPrice(int quantity) {
        double total = 0;
        try {
            String sql = "SELECT * FROM fares";
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                total = Double.parseDouble(rs.getString("b_fare")) * quantity;
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        System.out.println(total + "00.000 VND");
    }
}
