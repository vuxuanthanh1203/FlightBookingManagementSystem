package group3.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import group3.bl.FlightBL;
import group3.persistance.ClearScreen;
import group3.ui.FlightUI;
import group3.ui.Header;
import group3.ui.MenuUI;

public class FlightDAL {
    private static Connection connection = null;
    private static PreparedStatement pstmt = null;
    private static ResultSet rs = null;

    private static Connection getConnection() throws SQLException {
        Connection conn = DbUtil.getInstance().getConnection();
        return conn;
    }

    public void displayFlight() {
        try {
            String sql = "CALL search_flight('" + FlightUI.route + "','" + FlightUI.date + "')";
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (!rs.next()) {
                ClearScreen.clear();
                if (UserDAL.isLogin) {
                    System.out.println("\n-- No matching results --\n");
                    MenuUI.cusScreen();
                } else {
                    Header.header();
                    System.out.println("\n-- No matching results --\n");
                    System.out.print("\n- Enter to be back !");
                    getScanner().nextLine();
                    MenuUI.menu();
                }
            } else {
                while (rs.next()) {
                    String economy = rs.getDouble("e_fare") + "00.000 VND";
                    String premium = rs.getDouble("p_fare") + "00.000 VND";
                    String business = rs.getDouble("b_fare") + "00.000 VND";
                    String e_remain = rs.getInt("remaining_e_seat") + "/" + rs.getInt("total_e_seat");
                    String p_remain = rs.getInt("remaining_p_seat") + "/" + rs.getInt("total_p_seat");
                    String b_remain = rs.getInt("remaining_b_seat") + "/" + rs.getInt("total_b_seat");
                    if (rs.getInt("remaining_e_seat") == 0) {
                        e_remain = "SOLD OUT";
                    }
                    if (rs.getInt("remaining_p_seat") == 0) {
                        p_remain = "SOLD OUT";
                    }
                    if (rs.getInt("remaining_b_seat") == 0) {
                        b_remain = "SOLD OUT";
                    }
                    String day = "";
                    System.out.printf("\n %3d %15s %14s %14s %16s %24s %22s %23s \n", rs.getInt("flight_id"),
                            rs.getString("flight_date"), rs.getString("departure_time"), rs.getString("arrival_time"),
                            rs.getString("flight_num"), economy, premium, business);
                    System.out.printf("\n %3s %15s %11s %14s %20s %21s %22s %23s \n", day, day,
                            rs.getString("departure_loc"), rs.getString("arrival_loc"), rs.getString("flight_time"),
                            e_remain, p_remain, b_remain);
                    line();
                }
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public static void updateERemain(int quantity, int flightID) {
        try {
            String sql = "SELECT * FROM flightStatus WHERE flight_id = '" + flightID + "'";
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                String sql1 = "UPDATE flightStatus SET remaining_e_seat = ? WHERE flight_id = '" + flightID + "'";
                connection = getConnection();
                pstmt = connection.prepareStatement(sql1);
                pstmt.setInt(1, rs.getInt("remaining_e_seat") - quantity);
                pstmt.executeUpdate();

            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public static void updatePRemain(int quantity, int flightID) {
        try {
            String sql = "SELECT * FROM flightStatus WHERE flight_id = '" + flightID + "'";
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                String sql1 = "UPDATE flightStatus SET remaining_p_seat = ? WHERE flight_id = '" + flightID + "'";
                connection = getConnection();
                pstmt = connection.prepareStatement(sql1);
                pstmt.setInt(1, rs.getInt("remaining_p_seat") - quantity);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public static void updateBRemain(int quantity, int flightID) {
        try {
            String sql = "SELECT * FROM flightStatus WHERE flight_id = '" + flightID + "'";
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                String sql1 = "UPDATE flightStatus SET remaining_b_seat = ? WHERE flight_id = '" + flightID + "'";
                connection = getConnection();
                pstmt = connection.prepareStatement(sql1);
                pstmt.setInt(1, rs.getInt("remaining_b_seat") - quantity);
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public static int checkERemain(int flightID) {
        int eRemain = 0;
        try {
            String sql = "SELECT * FROM flightStatus WHERE flight_id = '" + flightID + "'";
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                eRemain = rs.getInt("remaining_e_seat");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return eRemain;
    }

    public static int checkPRemain(int flightID) {
        int pRemain = 0;
        try {
            String sql = "SELECT * FROM flightStatus WHERE flight_id = '" + flightID + "'";
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                pRemain = rs.getInt("remaining_p_seat");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return pRemain;
    }

    public static int checkBRemain(int flightID) {
        int bRemain = 0;
        try {
            String sql = "SELECT * FROM flightStatus WHERE flight_id = '" + flightID + "'";
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                bRemain = rs.getInt("remaining_b_seat");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return bRemain;
    }

    public static boolean chooseFlight(int flightID) {
        try {
            String sql = "SELECT * FROM flights WHERE flight_id = '" + flightID + "'";
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return false;
    }

    public void line() {
        System.out.println(
                "------------------------------------------------------------------------------------------------------------------------------------------------");
    }

    public static Scanner getScanner() {
        return new Scanner(System.in);
    }
}
