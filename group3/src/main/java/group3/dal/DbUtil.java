package group3.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
    private static DbUtil DbUtil = null;

    public Connection getConnection() {
        Connection conn = null;
        try {  
            String strConn = "jdbc:mysql://localhost:3306/flightbooking";
            conn = DriverManager.getConnection(strConn, "root", "12031998");
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
        return conn;
    }

    public static DbUtil getInstance() {
		if (DbUtil == null) {
			DbUtil = new DbUtil();
		}
		return DbUtil;
	}
}
