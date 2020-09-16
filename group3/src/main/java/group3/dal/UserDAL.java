package group3.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import group3.persistance.ClearScreen;
import group3.ui.UserUI;

public class UserDAL {
    private static Connection connection = null;
    private static PreparedStatement pstmt = null;
    private static ResultSet rs = null;

    private static Connection getConnection() throws SQLException {
        Connection conn = DbUtil.getInstance().getConnection();
        return conn;
    }

    public boolean login(String email, String pass) {
        boolean isLogin = false;
        try {
            String sql = "SELECT email, pass FROM users WHERE email = '" + email + "'AND pass = '" + pass + "'";
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                isLogin = true;
                if (selectRole(email) == 1) {
                    UserUI.adminScreen();
                } else {
                    UserUI.customerScreen();
                }
            } else {
                ClearScreen.clear();
                System.out.println("\n-- Account does not exist !!! --\n");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return isLogin;
    }

    public void register(String email, String pass, String name, String tel, String id, String address) {
        try {
            String sql = "INSERT INTO users(email, pass, full_name, tel, id_card, address, role_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, pass);
            pstmt.setString(3, name);
            pstmt.setString(4, tel);
            pstmt.setString(5, id);
            pstmt.setString(6, address);
            pstmt.setInt(7, 2);
            int k = pstmt.executeUpdate();
            if (k == 1) {
                System.out.println("\n-- Insert Successfully ! --\n");
            } else {
                System.out.println("\n-- Insert Failed !!! --\n");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public void modifyAccount(String email, String name, String tel, String address) {
        try {
            String sql = "UPDATE users SET full_name = ?, tel = ?, address = ? WHERE email = '" + email + "'";
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, tel);
            pstmt.setString(3, address);
            int k = pstmt.executeUpdate();
            if (k == 1) {
                System.out.println("\n-- Update Successfully ! --\n");
            } else {
                System.out.println("\n-- Update Failed !!! --\n");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public void changePassword(String email, String oldPass, String newPass) {
        try {
            String sql = "SELECT * FROM users WHERE email = '" + email + "'";
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                if (rs.getString("pass").equals(oldPass)) {
                    String sql1 = "UPDATE users SET pass = ? WHERE email = '" + email + "'";
                    connection = getConnection();
                    pstmt = connection.prepareStatement(sql1);
                    pstmt.setString(1, newPass);
                    int k = pstmt.executeUpdate();
                    if (k == 1) {
                        System.out.println("\n-- Update Successful ! --\n");
                    } else {
                        System.out.println("\n-- Update Failed !!! --\n");
                    }
                } else {
                    System.out.println("\n-- Incorrect Password !!! --\n");
                }
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public static void selectAccount(String email) {
        try {
            String sql = "SELECT * FROM users WHERE email = '" + email + "'";
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("- Email: " + rs.getString("email"));
                System.out.println("- Name: " + rs.getString("full_name"));
                System.out.println("- Tel: " + rs.getString("tel"));
                System.out.println("- ID Card: " + rs.getString("id_card"));
                System.out.println("- Address: " + rs.getString("address"));
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public int selectRole(String email) {
        int result = 0;
        try {
            String sql = "SELECT role_id FROM users WHERE email = '" + email + "'";
            connection = getConnection();
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = rs.getInt("role_id");
            } else {
                System.out.println("\n Cannot get role !!!\n");
            }
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return result;
    }

    public static boolean checkEmail(String email) {
        try {
            String sql = "SELECT email FROM users WHERE email = '" + email + "'";
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

    public static boolean checkPass(String pass) {
        try {
            String sql = "SELECT pass FROM users WHERE pass = '" + pass + "'";
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
}
