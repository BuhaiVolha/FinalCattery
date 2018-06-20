package by.epam.cattery.dao.connection;

import static by.epam.cattery.dao.connection.Provider.*;
import java.sql.*;

public class ConnectionProvider {
    private static Connection con = null;

    static {
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.err.println("connection failed " + e);
        }
    }

    public static Connection getCon() {
        return con;
    }

    public static void close(Connection con, Statement st, ResultSet rs) {
        try {
            if (con != null) {
                con.close();
            }

        } catch (SQLException e) {
            System.err.println("closing connection failed " + e);
        }

        try {
            if (st != null) {
                st.close();
            }

        } catch (SQLException e) {
            System.err.println("closing statement failed " + e);
        }
        try {
            if (rs != null) {
                rs.close();
            }

        } catch (SQLException e) {
            System.err.println("closing resultset failed " + e);
        }
    }
}
